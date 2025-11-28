package com.smartcard.smart_card_fx.utils

import java.security.PublicKey

object RSAUtils {

    fun generatePublicKeyFromBytes(data: ByteArray?): PublicKey {
        println("Try to generate public key")
        println("Data length: " + data!!.size)
    }
}