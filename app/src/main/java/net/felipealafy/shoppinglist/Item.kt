package net.felipealafy.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Item (
    var description: String,
    var id: Int
) {
    var name by mutableStateOf("")
    var bought by mutableStateOf(false)
}

fun Item.getDescription() {
    val formatter = DateTimeFormatter.ofPattern("EEEE (dd-MM-yyyy) HH:mm")
    this.description = formatter.format(LocalDateTime.now())
}