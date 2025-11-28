package com.smartcard.smart_card_fx.view.base

import javafx.stage.Stage

abstract class PopupController {
    var stage: Stage? = null
    fun dismiss() {
        stage?.close()
    }
}