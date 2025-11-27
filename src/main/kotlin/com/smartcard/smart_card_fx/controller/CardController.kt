package com.smartcard.smart_card_fx.controller

import com.smartcard.smart_card_fx.model.ApplicationState
import javax.smartcardio.CommandAPDU
import javax.smartcardio.TerminalFactory

class CardController {

    private val appState by lazy { ApplicationState.IDLE }

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

        val terminal = terminals.list()[0]
        println("Đang kết nối tới thiết bị thẻ: ${terminal.name}")
        println("Card present: " + terminal.isCardPresent)
        if (!terminal.isCardPresent) {
            println("Không có thẻ nào được chèn vào thiết bị.")
            onResult(false)
            return
        }

        appState.card = terminal.connect("T=0")
        println("Kết nối thành công tới thẻ: " + appState.card)
        val channel = appState.card?.basicChannel
        val command = CommandAPDU(0x00, 0xA4, 0x04, 0x00, appletAID)
        val response = channel?.transmit(command)?.sw
        println("Kết quả trả về: ${response?.toHexString()}")
        if (response == 0x9000) {
            println("Gửi lệnh select thành công!")
            onResult(true)
        } else {
            println("Gửi lệnh thẻ thất bại. SW: $response")
            onResult(false)
        }
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