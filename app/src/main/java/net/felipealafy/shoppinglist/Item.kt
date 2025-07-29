package net.felipealafy.shoppinglist

data class Item (
    var name: String,
    var description: String,
    var bought: Boolean = false
)