package com.smartcard.smart_card_fx.view.viewcontroller

import com.smartcard.smart_card_fx.view.base.PopupController
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Stage

class PopupNoticeController : PopupController() {

    lateinit var btnOK: Button
    lateinit var label: Label
    lateinit var onClickButton: () -> Unit

    fun initialize(label: String, btnLabel: String, stage: Stage, onClickButton: () -> Unit) {
        this.stage = stage
        this.label.text = label
        this.btnOK.text = btnLabel
        this.onClickButton = onClickButton
    }

    fun onBtnClick() {
        onClickButton.invoke()
    }
}