package com.smartcard.smart_card_fx

import com.smartcard.smart_card_fx.global.GlobalLoader
import com.smartcard.smart_card_fx.view.viewcontroller.HomeController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainApplication : Application() {

    override fun start(mainStage: Stage) {
        initLoader()
        val scene = Scene(GlobalLoader.fxmlLoaderHome.load())
        mainStage.scene = scene
        GlobalLoader.fxmlLoaderHome.getController<HomeController>().init()
        mainStage.show()
    }

    private fun initLoader() {
        GlobalLoader.fxmlLoaderHome = FXMLLoader(MainApplication::class.java.getResource("home-view.fxml"))
    }
}