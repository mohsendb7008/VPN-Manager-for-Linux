package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import kotlin.reflect.KType

@Composable
fun InputField(name: String, type: KType, modifier: Modifier = Modifier) {
    when (type.toString()) {
        "kotlin.String" -> {
            val text = remember { mutableStateOf("") }
            OutlinedTextField(value = text.value, onValueChange = {
                text.value = it
            }, label = {
                Text(name)
            }, isError = text.value.trim() == "", modifier = modifier)
        }
        "kotlin.String?" -> {
            val text = remember { mutableStateOf("") }
            OutlinedTextField(value = text.value, onValueChange = {
                text.value = it
            }, label = {
                Text(name)
            }, modifier = modifier)
        }
        "kotlin.Int" -> {
            val number = remember { mutableStateOf("") }
            OutlinedTextField(
                value = number.value,
                onValueChange = {
                    number.value = it
                },
                label = {
                    Text(name)
                }, isError = runCatching {
                    number.value.toInt()
                    false
                }.getOrDefault(true),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = modifier
            )
        }
        "kotlin.Boolean" -> {
            val bool = remember { mutableStateOf(false) }
            Row(modifier = modifier) {
                Checkbox(checked = bool.value, onCheckedChange = {
                    bool.value = it
                })
                Text(name)
            }
        }
        else -> throw NotImplementedError("$type is not supported")
    }
}