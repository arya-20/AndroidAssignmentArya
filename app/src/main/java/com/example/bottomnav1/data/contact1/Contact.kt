package com.example.bottomnav1.data.contact1

data class Contact(
    var firstName: String? =null,
    var surname: String? =null,
    var telNo: String? =null
) {
    var id:String? =null //UUID
    override fun toString(): String = "$firstName $surname $telNo"
}