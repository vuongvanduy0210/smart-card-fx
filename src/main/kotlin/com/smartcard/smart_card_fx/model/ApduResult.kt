package com.smartcard.smart_card_fx.model

sealed class ApduResult {
    class Failed(message: String, response: ByteArray? = null): ApduResult()
    class Success(response: ByteArray?): ApduResult()
}