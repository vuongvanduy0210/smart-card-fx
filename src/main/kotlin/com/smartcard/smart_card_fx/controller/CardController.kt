package com.smartcard.smart_card_fx.controller

import com.smartcard.smart_card_fx.model.ApduResult
import com.smartcard.smart_card_fx.model.ApplicationState
import java.io.ByteArrayOutputStream
import javax.smartcardio.CardException
import javax.smartcardio.CommandAPDU
import javax.smartcardio.TerminalFactory

class CardController {

    @OptIn(ExperimentalStdlibApi::class)
    fun connectCard(onResult: (Boolean) -> Unit) {
        val appletAID =
            byteArrayOf(0x11.toByte(), 0x22.toByte(), 0x33.toByte(), 0x44.toByte(), 0x55.toByte(), 0x00.toByte())
        val factory = TerminalFactory.getDefault()
        val terminals = factory.terminals()
        println("Số lượng thiết bị thẻ có sẵn: ${terminals.list().size}")
        if (terminals.list().isEmpty()) {
            println("Không tìm thấy thiết bị thẻ.")
            onResult(false)
            return
        }
        terminals.list().onEach {
            println("Thiết bị thẻ: ${it.name}")
        }

        val terminal = terminals.list()[1]
        println("Đang kết nối tới thiết bị thẻ: ${terminal.name}")
        println("Card present: " + terminal.isCardPresent)
        if (!terminal.isCardPresent) {
            println("Không có thẻ nào được chèn vào thiết bị.")
            onResult(false)
            return
        }

        ApplicationState.card = terminal.connect("T=1")
        println("Kết nối thành công tới thẻ: " + ApplicationState.card)
        when (val result = sendApdu(0x00, 0xA4, 0x04, 0x00, appletAID)) {
            is ApduResult.Success -> {
                println("Gửi lệnh select thành công!")
                ApplicationState.isCardInserted = true
                onResult(true)
            }
            is ApduResult.Failed -> {
                println("Gửi lệnh thẻ thất bại. SW: $result")
                onResult(false)
            }
        }
    }

    fun sendApdu(cla: Int, ins: Int, p1: Int, p2: Int, data: ByteArray?): ApduResult {
        if (ApplicationState.card == null) return ApduResult.Failed("Không tìm thấy card!")
        return try {
            val channel = ApplicationState.card!!.basicChannel
            val apduStream = ByteArrayOutputStream()
            apduStream.write(cla)
            apduStream.write(ins)
            apduStream.write(p1)
            apduStream.write(p2)
            val dataLength = data?.size ?: 0

            apduStream.write((dataLength shr 16) and 0xFF)
            apduStream.write((dataLength shr 8) and 0xFF)
            apduStream.write(dataLength and 0xFF)

            if (data != null) {
                apduStream.write(data)
            }

            val command = CommandAPDU(apduStream.toByteArray())
            println("Sending APDU: $command")
            val response = channel.transmit(command)
            println("APDU Response SW: ${Integer.toHexString(response.sw)}")
            if (response.data.isNotEmpty()) {
                // Arrays.toString -> contentToString() trong Kotlin
                println("APDU Response Data: ${response.data.contentToString()}")
            }
            if (response.sw == 0x9000) {
                ApduResult.Success(response.data)
            } else {
                System.err.println("APDU failed with status word: ${Integer.toHexString(response.sw)}")
                ApduResult.Failed(message = "Lỗi gửi apdu", response = response.data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApduResult.Failed(message = e.message ?: "Lỗi gửi apdu không xác định!!")
        }
    }

    fun isCardConnected(): Boolean {
        return ApplicationState.isCardInserted && ApplicationState.isCardVerified
    }

    companion object {
        private var instance: CardController? = null

        fun getInstance(): CardController {
            return instance ?: synchronized(this) {
                instance ?: CardController().also { instance = it }
            }
        }
    }
}