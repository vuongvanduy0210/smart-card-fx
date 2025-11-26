package com.smartcard.smart_card_fx.model

import javax.smartcardio.Card

sealed class ApplicationState {
    data object IDLE: ApplicationState()
    data object CardInserted : ApplicationState()
    data object AppLoggedIn: ApplicationState()
    data object CardVerified: ApplicationState()

    var cardNumber: String? = null
    var card: Card? = null
}