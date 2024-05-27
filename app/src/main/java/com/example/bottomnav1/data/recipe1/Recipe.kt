package com.example.bottomnav1.data.recipe1

enum class Category{
    NONE,
    BULK,
    WEIGHTLOSS,
    VEGAN,
    WEIGHTGAIN
}
data class Recipe(

    var id:String? =null,
    var name: String? = null,
    var category: Category? =null,
    var ingredients: String? = null,
    var instructions: String? = null
) {
    override fun toString(): String = "$name $category $ingredients $instructions"
}