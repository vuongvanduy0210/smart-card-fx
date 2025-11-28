package com.smartcard.smart_card_fx.global

import javafx.fxml.FXMLLoader

class GlobalLoader {
    companion object {
        lateinit var fxmlLoaderHome: FXMLLoader
        lateinit var fxmlLoaderPopupEnterPin: FXMLLoader
        lateinit var fxmlLoaderPopupNotice: FXMLLoader
        lateinit var fxmlSceneNoCardInserted: FXMLLoader
    }
}