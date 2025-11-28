package com.smartcard.smart_card_fx.utils

import com.smartcard.smart_card_fx.MainApplication
import com.smartcard.smart_card_fx.global.GlobalLoader
import com.smartcard.smart_card_fx.view.viewcontroller.PopupNoticeController
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Modality
import javafx.stage.Stage

object ViewUtils {

    fun alert(message: String) {
        Platform.runLater {
            val alert = Alert(Alert.AlertType.INFORMATION)
            alert.title = "Thông báo"
            alert.headerText = null
            alert.contentText = message
            alert.showAndWait()
        }
    }

    fun showPopupNotice(title: String, onButtonAction: () -> Unit = {}) {
        Platform.runLater {
            GlobalLoader.fxmlLoaderPopupNotice =
                FXMLLoader(MainApplication::class.java.getResource("popup-notice.fxml"))
            val stage = Stage().apply {
                initModality(Modality.APPLICATION_MODAL)
                this.title = "Thông báo"
                scene = Scene(GlobalLoader.fxmlLoaderPopupNotice.load())
            }
            GlobalLoader.fxmlLoaderPopupNotice.getController<PopupNoticeController>().apply {
                initialize(title, "OK", stage) {
                    onButtonAction.invoke()
                    this.dismiss()
                }
            }
            stage.showAndWait()
        }
    }
}