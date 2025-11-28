package com.smartcard.smart_card_fx.model

sealed class ApduResult {
    class Failed(val message: String, val response: ByteArray? = null): ApduResult()
    class Success(val response: ByteArray?): ApduResult()
}