package com.smartcard.smart_card_fx.validator

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Validator {
    fun validateFieldNotEmpty(field: String?, fieldName: String): String? {
        // Kotlin có hàm tiện ích kiểm tra cả null và empty
        if (field.isNullOrEmpty()) {
            return "Không được bỏ trống $fieldName"
        }
        return null
    }

    fun validatePinCode(pin: String?): String? {
        if (pin.isNullOrEmpty()) {
            return "Không được bỏ trống mã PIN"
        }
        if (pin.length != 6) {
            return "Mã PIN phải có 6 ký tự"
        }

        // Cách viết chuẩn Kotlin: kiểm tra tất cả ký tự có phải là số không
        // Thay thế cho regex "\\d*"
        if (!pin.all { it.isDigit() }) {
            return "Mã PIN chỉ chứa ký tự số"
        }
        return null
    }

    fun validateBirthDate(birthDate: String?): String? {
        println("birthDate: $birthDate") // String template
        if (birthDate.isNullOrEmpty()) {
            return "Không được bỏ trống ngày sinh"
        }

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return try {
            val date = LocalDate.parse(birthDate, formatter)
            if (date.isAfter(LocalDate.now())) {
                "Ngày sinh không hợp lệ"
            } else {
                null
            }
        } catch (e: DateTimeParseException) {
            e.printStackTrace()
            "Invalid birth date"
        }
    }

    fun validatePinMatch(pin: String?, confirmPin: String?): String? {
        // Kotlin xử lý so sánh chuỗi bằng '==' hoặc '!=' rất an toàn (tự check null)
        if (pin.isNullOrEmpty() || pin != confirmPin) {
            return "Mật khẩu không khớp"
        }
        return null
    }
}