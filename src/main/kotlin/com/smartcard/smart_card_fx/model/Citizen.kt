package com.smartcard.smart_card_fx.model

import io.ebean.Model
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob

@Entity
data class Citizen(
    @Id
    var citizenId: String? = null,
    var fullName: String? = null,
    var gender: String? = null,
    var birthDate: String? = null,
    var address: String? = null,
    var hometown: String? = null,
    var nationality: String? = null,
    var ethnicity: String? = null,
    var religion: String? = null,
    var identification: String? = null,
    @Lob
    var avatar: ByteArray? = null
) : Model() {

    fun toCardInfo(): String {
        return "$citizenId$$fullName$$gender$$birthDate$$address$$hometown$$nationality$$ethnicity$$religion$$identification"
    }

    fun fromCardInfo(cardInfo: String) {
        val parts = cardInfo.split("\\$".toRegex()).toTypedArray()
        if (parts.isNotEmpty()) citizenId = parts.getOrNull(0)
        if (parts.size > 1) fullName = parts.getOrNull(1)
        if (parts.size > 2) gender = parts.getOrNull(2)
        if (parts.size > 3) birthDate = parts.getOrNull(3)
        if (parts.size > 4) address = parts.getOrNull(4)
        if (parts.size > 5) hometown = parts.getOrNull(5)
        if (parts.size > 6) nationality = parts.getOrNull(6)
        if (parts.size > 7) ethnicity = parts.getOrNull(7)
        if (parts.size > 8) religion = parts.getOrNull(8)
        if (parts.size > 9) identification = parts.getOrNull(9)
    }



    // --- JAVAFX PROPERTIES ---
    fun citizenIdProperty(): StringProperty = SimpleStringProperty(citizenId)
    fun fullNameProperty(): StringProperty = SimpleStringProperty(fullName)
    fun genderProperty(): StringProperty = SimpleStringProperty(gender)
    fun birthDateProperty(): StringProperty = SimpleStringProperty(birthDate)
    fun addressProperty(): StringProperty = SimpleStringProperty(address)
    fun hometownProperty(): StringProperty = SimpleStringProperty(hometown)
    fun nationalityProperty(): StringProperty = SimpleStringProperty(nationality)
    fun ethnicityProperty(): StringProperty = SimpleStringProperty(ethnicity)
    fun religionProperty(): StringProperty = SimpleStringProperty(religion)
    fun identificationProperty(): StringProperty = SimpleStringProperty(identification)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Citizen

        if (citizenId != other.citizenId) return false
        if (fullName != other.fullName) return false
        if (gender != other.gender) return false
        if (birthDate != other.birthDate) return false
        if (address != other.address) return false
        if (hometown != other.hometown) return false
        if (nationality != other.nationality) return false
        if (ethnicity != other.ethnicity) return false
        if (religion != other.religion) return false
        if (identification != other.identification) return false
        if (!avatar.contentEquals(other.avatar)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = citizenId?.hashCode() ?: 0
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (gender?.hashCode() ?: 0)
        result = 31 * result + (birthDate?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (hometown?.hashCode() ?: 0)
        result = 31 * result + (nationality?.hashCode() ?: 0)
        result = 31 * result + (ethnicity?.hashCode() ?: 0)
        result = 31 * result + (religion?.hashCode() ?: 0)
        result = 31 * result + (identification?.hashCode() ?: 0)
        result = 31 * result + (avatar?.contentHashCode() ?: 0)
        return result
    }
}