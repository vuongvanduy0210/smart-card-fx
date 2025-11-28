package com.smartcard.smart_card_fx.model

import javax.smartcardio.Card

object ApplicationState {
    var isCardInserted: Boolean = false
    var isAppLoggedIn: Boolean = false
    var isCardVerified: Boolean = false

    var cardNumber: String? = null
    var card: Card? = null

    fun reset() {
        isCardInserted = false
        isAppLoggedIn = false
        isCardVerified = false
        cardNumber = null
        card?.disconnect(false)
        card = null
    }
}