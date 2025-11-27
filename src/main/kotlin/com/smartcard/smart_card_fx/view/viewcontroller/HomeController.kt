package com.smartcard.smart_card_fx.view.viewcontroller

import com.smartcard.smart_card_fx.controller.CardController
import com.smartcard.smart_card_fx.model.Citizen
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox

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

    fun init() {

    }

    @FXML
    protected fun onConnectCardClick() {
        CardController.getInstance().connectCard {
            println(it)
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