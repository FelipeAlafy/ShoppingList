package net.felipealafy.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.felipealafy.shoppinglist.ui.theme.Coral
import net.felipealafy.shoppinglist.ui.theme.Marine
import net.felipealafy.shoppinglist.ui.theme.ShoppingListTheme
import net.felipealafy.shoppinglist.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val itemsList = remember { mutableStateListOf<Item>() }
                    val textInputContent = rememberSaveable { mutableStateOf("") }
                    val handlerItem = remember {mutableStateOf(Item(
                        name = "",
                        description = "",
                        bought = false
                    ))}

                    Column (
                        Modifier.fillMaxSize().padding(innerPadding),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Logo()

                        Input(textInputContent = textInputContent)
                        SaveButton (
                            onClick= {
                                handlerItem.value.name = textInputContent.value
                                if (handlerItem.value.description.isEmpty()) {
                                    handlerItem.value.getDescription()
                                }
                                itemsList.add(handlerItem.value)
                            }
                        )

                        Title(text = R.string.to_buy)
                        LazyColumn {
                            items(itemsList) { itemData ->
                                if (itemData.bought) return@items
                                ItemComponent(
                                    itemData = itemData,
                                    onCheck = { checkStatus ->
                                        itemData.bought = checkStatus
                                    },
                                    onDeleteButtonClicked = {
                                        itemsList.remove(itemData)
                                    },
                                    onEditModeButtonClicked = {
                                        textInputContent.value = itemData.name
                                        handlerItem.value.copy(
                                            itemData.name,
                                            itemData.description,
                                            itemData.bought
                                        )
                                        itemsList.remove(itemData)
                                    }
                                )
                            }
                        }
                        Title(text = R.string.bought_items)
                        LazyColumn {
                            items(itemsList) { itemData ->
                                if (!itemData.bought) return@items
                                ItemComponent(
                                    itemData = itemData,
                                    onCheck = { checkStatus ->
                                        itemData.bought = checkStatus
                                    },
                                    onDeleteButtonClicked = {
                                        itemsList.remove(itemData)
                                    },
                                    onEditModeButtonClicked = {
                                        textInputContent.value = itemData.name
                                        handlerItem.value.copy(
                                            itemData.name,
                                            itemData.description,
                                            itemData.bought
                                        )
                                        itemsList.remove(itemData)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Logo() {
    Image(
        modifier = Modifier.size(160.dp, 160.dp),
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo)
    )
}

@Composable
fun Input(textInputContent: MutableState<String>) {
    OutlinedTextField(
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        value = textInputContent.value,
        onValueChange = {
            textInputContent.value = it
        },
        textStyle = Typography.bodyLarge,
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(bottom = 32.dp),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonColors(
            contentColor = Color.White,
            containerColor = Coral,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.DarkGray,
        )
    ) {
        Text(text = stringResource(R.string.save_button))
    }
}

@Composable
fun ActionButtonDelete(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_action_button),
            tint = Marine
        )
    }
}

@Composable
fun ActionButtonEdit(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            Icons.Filled.Edit,
            contentDescription = stringResource(R.string.delete_action_button),
            tint = Marine
        )
    }
}

@Composable
fun ItemCheckbox(modifier: Modifier = Modifier, itemData: Item, onCheck: (Boolean) -> Unit) {
    Checkbox(
        modifier = modifier,
        checked = itemData.bought,
        onCheckedChange = onCheck
    )
}

@Composable
fun ItemName(modifier: Modifier = Modifier, item: Item) {
    Text(
        modifier = modifier,
        text= item.name,
        style = Typography.bodyLarge
    )
}

@Composable
fun ItemDescription(modifier: Modifier = Modifier, item: Item) {
    Text(
        modifier = modifier,
        text= item.description,
        style = Typography.labelSmall
    )
}

@Composable
fun ItemComponent(
    modifier: Modifier = Modifier, itemData: Item,
    onCheck: (Boolean) -> Unit,
    onEditModeButtonClicked: () -> Unit,
    onDeleteButtonClicked: () -> Unit
    ) {
    Row(
        modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.Start,
         verticalAlignment = Alignment.CenterVertically
    ) {
        ItemCheckbox(itemData = itemData, onCheck = onCheck)
        Column {
            ItemName(item = itemData)
            ItemDescription(item = itemData)
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButtonDelete(onClick = onEditModeButtonClicked)
            ActionButtonEdit(onClick = onDeleteButtonClicked)
        }
    }
}

@Composable
fun Title(@StringRes text: Int) {
    val endMutiplyFactor: Float  = stringResource(text).length.toFloat() * 42f
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .drawBehind {
                val strokeWidth = 2.dp.toPx()
                val dashWidth = 6.dp.toPx()
                val dashGap = 4.dp.toPx()


                val pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(dashWidth, dashGap), 0f
                )

                drawLine(
                    color= Coral,
                    start = Offset(0f, size.height),
                    end = Offset(endMutiplyFactor, size.height),
                    strokeWidth = strokeWidth,
                    pathEffect = pathEffect
                )
            },
        textAlign = TextAlign.Start,
        text = stringResource(text),
        style = Typography.headlineLarge
    )
}

