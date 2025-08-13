package net.felipealafy.shoppinglist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ShoppingListViewModel : ViewModel() {

    private val _defaultItem = Item(description = "", id = 0)
    private val _itemsList: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    private val _textInputContent: MutableStateFlow<String> = MutableStateFlow("")
    private val _currentItem: MutableStateFlow<Item> = MutableStateFlow(_defaultItem.copy())

    val itemList: StateFlow<List<Item>> = _itemsList
    val textInputContent: StateFlow<String> = _textInputContent

    fun addItem() {
        val newItem = _currentItem.value.copy(
            description = _currentItem.value.description,
            id = _itemsList.value.size + 1
        ).apply {
            name = textInputContent.value
        }

        if (newItem.description.isEmpty()) {
            newItem.getDescription()
        }
        _itemsList.update { list ->
            list + newItem
        }

        _currentItem.value = _defaultItem.copy()
        _textInputContent.value = ""
    }

    fun removeItem(item: Item) {
        _itemsList.update { list ->
            list - item
        }
        _currentItem.value = _defaultItem.copy()
    }

    fun editItem(oldItem: Item) {
        _textInputContent.update {
            oldItem.name
        }

        _currentItem.update {
            oldItem
        }

        removeItem(oldItem)
    }

    fun onCheck(item: Item) {
        item.bought = !item.bought
    }

    val onTextChanged: (String) -> Unit = { newText ->
        _textInputContent.update {
            newText
        }
    }
}