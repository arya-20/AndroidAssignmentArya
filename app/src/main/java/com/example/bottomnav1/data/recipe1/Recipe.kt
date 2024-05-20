package com.example.bottomnav1.data.recipe1

enum class Category{
    NONE,
    BULK,
    WEIGHTLOSS,
    VEGAN,
    WEIGHTGAIN
}
data class Recipe(

    val name: String? = null,
    val category: Category? =null,
    val ingredients: String? = null,
    val instructions: String? = null
) {
    var id:String? =null //UUID
    override fun toString(): String = "$name $category $ingredients $instructions"
}