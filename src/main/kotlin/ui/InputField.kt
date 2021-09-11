package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import kotlin.reflect.KParameter

@Composable
fun InputField(
    name: String,
    parameter: KParameter,
    map: HashMap<KParameter, MutableState<Any?>>,
    modifier: Modifier = Modifier
) {
    when (val type = parameter.type.toString()) {
        "kotlin.String" -> {
            OutlinedTextField(value = map[parameter]!!.value as String, onValueChange = {
                map[parameter]!!.value = it
            }, label = {
                Text(name)
            }, isError = (map[parameter]!!.value as String).trim() == "", modifier = modifier)
        }
        "kotlin.String?" -> {
            OutlinedTextField(value = map[parameter]!!.value as String? ?: "", onValueChange = {
                map[parameter]!!.value = if (it.trim() == "") null else it
            }, label = {
                Text(name)
            }, modifier = modifier)
        }
        "kotlin.Int" -> {
            OutlinedTextField(
                value = (map[parameter]!!.value as Int?)?.toString() ?: "",
                onValueChange = {
                    map[parameter]!!.value = runCatching { it.toInt() }.getOrDefault(null)
                },
                label = {
                    Text(name)
                },
                isError = runCatching {
                    map[parameter]!!.value.toString().toInt()
                    false
                }.getOrDefault(true),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = modifier
            )
        }
        "kotlin.Boolean" -> {
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = map[parameter]!!.value as Boolean, onCheckedChange = {
                    map[parameter]!!.value = it
                })
                Text(name)
            }
        }
        else -> throw NotImplementedError("$type is not supported")
    }
}