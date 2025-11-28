package com.smartcard.smart_card_fx.view.viewcontroller

import com.smartcard.smart_card_fx.utils.ViewUtils
import com.smartcard.smart_card_fx.validator.Validator
import com.smartcard.smart_card_fx.view.base.PopupController
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.stage.Stage

class EnterPinPopupController : PopupController() {

    lateinit var rightBtn: Button
    lateinit var leftBtn: Button
    lateinit var inputField: PasswordField
    lateinit var label: Label
    lateinit var onClickLeft: (EnterPinPopupController) -> Unit
    lateinit var onClickRight: (EnterPinPopupController, String) -> Unit

    fun initialize(
        label: String,
        hint: String,
        leftLabel: String,
        rightLabel: String,
        stage: Stage,
        onClickLeftBtn: (EnterPinPopupController) -> Unit,
        onClickRightBtn: (EnterPinPopupController, String) -> Unit
    ) {
        this.stage = stage
        this.label.text = label
        this.inputField.text = hint
        this.leftBtn.text = leftLabel
        this.rightBtn.text = rightLabel
        this.onClickLeft = onClickLeftBtn
        this.onClickRight = onClickRightBtn
    }

    @FXML
    fun onLeftBtnClick() {
        onClickLeft.invoke(this)
    }

    @FXML
    fun onRightBtnClick() {
        Validator.validatePinCode(inputField.text)?.let {
            ViewUtils.alert(it)
            return
        }
        onClickRight.invoke(this, inputField.text)
    }
}