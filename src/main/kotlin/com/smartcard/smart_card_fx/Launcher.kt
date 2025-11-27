package com.smartcard.smart_card_fx

import javafx.application.Application
import java.io.PrintStream
import java.nio.charset.StandardCharsets

fun main() {
    System.setOut(PrintStream(System.out, true, StandardCharsets.UTF_8))
    Application.launch(MainApplication::class.java)
}
