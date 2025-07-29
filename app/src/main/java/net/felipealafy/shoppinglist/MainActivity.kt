package net.felipealafy.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.felipealafy.shoppinglist.ui.theme.Coral
import net.felipealafy.shoppinglist.ui.theme.ShoppingListTheme
import net.felipealafy.shoppinglist.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (Modifier.padding(innerPadding)) {
                        ItemCheckbox()
                    }
                }
            }
        }
    }
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.logo)
    )
}

@Composable
fun Input(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
        },
        textStyle = Typography.bodyLarge,
        shape = RoundedCornerShape(20.dp)
    )
}

@Composable
fun SaveButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
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
fun ActionButton(modifier: Modifier = Modifier, onClick: () -> Unit, deleteMode: Boolean) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        if (deleteMode) {
            Icon(Icons.Filled.Delete, contentDescription = stringResource(R.string.delete_action_button))
        } else {
            Icon(Icons.Filled.Edit, contentDescription = stringResource(R.string.delete_action_button))
        }
    }
}

@Composable
fun ItemCheckbox(modifier: Modifier = Modifier, checkInitial: Boolean = false) {
    var check by remember { mutableStateOf(checkInitial) }
    Checkbox(
        modifier = modifier,
        checked = check,
        onCheckedChange = {
            check = it
        }
    )
}

@Composable
fun ItemName(modifier: Modifier = Modifier, item: Item) {
    Text(
        modifier = modifier,
        text= item.name,
        style = Typography.displayLarge
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
fun Title(modifier: Modifier = Modifier, @StringRes text: Int) {
    Text(
        modifier = modifier,
        text = stringResource(text),
        style = Typography.headlineLarge
    )
}

