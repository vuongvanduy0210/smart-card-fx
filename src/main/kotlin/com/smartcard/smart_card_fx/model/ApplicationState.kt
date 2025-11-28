package com.smartcard.smart_card_fx.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.smartcardio.Card

object ApplicationState {
    private val _isCardInserted = MutableStateFlow(false)
    val isCardInserted = _isCardInserted.asStateFlow()
    fun setCardInserted(inserted: Boolean) {
        _isCardInserted.value = inserted
    }

    var isAppLoggedIn: Boolean = false
    var isCardVerified: Boolean = false

    var cardNumber: String? = null
    var card: Card? = null

    fun reset() {
        _isCardInserted.value = false
        isAppLoggedIn = false
        isCardVerified = false
        cardNumber = null
        card?.disconnect(false)
        card = null
    }
}