package net.felipealafy.shoppinglist

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Item (
    var name: String,
    var description: String,
    var bought: Boolean = false
)

fun Item.getDescription() {
    val formatter = DateTimeFormatter.ofPattern("EEEE (dd-MM-yyyy) HH:mm")
    this.description = formatter.format(LocalDateTime.now())
}