package com.smartcard.smart_card_fx.view.viewcontroller

import com.smartcard.smart_card_fx.MainApplication
import com.smartcard.smart_card_fx.controller.CardController
import com.smartcard.smart_card_fx.global.GlobalLoader
import com.smartcard.smart_card_fx.model.ApplicationState
import com.smartcard.smart_card_fx.model.Citizen
import com.smartcard.smart_card_fx.utils.ViewUtils
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.Stage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.javafx.JavaFx

open class HomeController {

    lateinit var lblInfo: Label
    lateinit var avatarImage: ImageView
    lateinit var btnLogout: Button
    lateinit var btnConnectCard: Button
    lateinit var cmbGender: ComboBox<String>
    lateinit var tblCitizenData: TableView<Citizen>
    lateinit var lblAdminName: Label
    lateinit var btnRemoveFilter: Button
    lateinit var btnSearchCitizen: Button
    lateinit var datePickerBirth: DatePicker
    lateinit var txtHometown: TextField
    lateinit var txtName: TextField
    lateinit var txtCitizenId: TextField
    lateinit var tabManageCitizen: Tab
    lateinit var vboxContent: VBox
    lateinit var tabPane: TabPane
    lateinit var imageHome: ImageView

    private val uiScope = CoroutineScope(Dispatchers.JavaFx + SupervisorJob())

    fun init() {
        ApplicationState.isCardInserted.onEach {
            updateUI(it)
        }.launchIn(uiScope)
    }

    @FXML
    protected fun onConnectCardClick() {
        if (CardController.getInstance().isCardConnected()) {

        } else {

        }

    }

    private fun handleInsertCard() {
        GlobalLoader.fxmlLoaderPopupEnterPin =
            FXMLLoader(MainApplication::class.java.getResource("popup-enter-pin.fxml"))
        val controller = GlobalLoader.fxmlLoaderPopupEnterPin.getController<EnterPinPopupController>()
        val popupStage = Stage().apply {
            initModality(Modality.APPLICATION_MODAL)
            title = "Kết nối thẻ"
            scene = Scene(GlobalLoader.fxmlLoaderPopupEnterPin.load())
        }
        controller.initialize(
            label = "Nhập mã pin (6 ký tự)",
            hint = "Mã pin",
            leftLabel = "Huỷ",
            rightLabel = "Xác nhận",
            stage = popupStage,
            onClickLeftBtn = { popup ->
                popup.dismiss()
            },
            onClickRightBtn = { popup, value ->
                CardController.getInstance().connectCard { isConnected ->
                    if (isConnected) {
                        if (!CardController.getInstance().isCardActive()) {
                            ViewUtils.showPopupNotice("Thẻ đã bị khoá!") {
                                CardController.getInstance().disconnectCard()
                                ApplicationState.setCardInserted(false)
                            }
                            popup.dismiss()
                            return@connectCard
                        }

                        println("Card is active")
                        CardController.getInstance().getCardId()?.let {
                            val isCardVerified = CardController.getInstance().
                        }

                    } else {
                        ViewUtils.showPopupNotice("Không thể kết nối thẻ!")
                    }
                }
            }
        )

        popupStage.showAndWait()
    }

    private fun updateUI(isInserted: Boolean) {
        if (isInserted) {
            println("Thẻ đã cắm (Flow update)")
            btnConnectCard.text = "Bỏ thẻ"
        } else {
            println("Thẻ đã rút (Flow update)")
            btnConnectCard.text = "Kết nối thẻ"
            showNoCardInserted()
        }
    }

    private fun showNoCardInserted() {
        try {
            GlobalLoader.fxmlSceneNoCardInserted =
                FXMLLoader(MainApplication::class.java.getResource("scene-no-card-inserted.fxml"))
            val anchorPane: AnchorPane? = GlobalLoader.fxmlSceneNoCardInserted.load()
            vboxContent.children.clear()
            vboxContent.children.add(anchorPane)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    protected fun onManageCitizenTabSelected() {

    }

    @FXML
    protected fun onSearchCitizen() {

    }

    @FXML
    protected fun onRemoveFilter() {

    }
}