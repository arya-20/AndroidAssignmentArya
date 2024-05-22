package com.example.bottomnav1.data.contact1

import com.example.bottomnav1.data.recipe1.Recipe

data class Contact(
    var email: String? =null,
    var password: String? =null,
    var recipe: List<Recipe>? =null
) {
    var id:String? =null //UuId
    override fun toString(): String = "$email $password $recipe"
}