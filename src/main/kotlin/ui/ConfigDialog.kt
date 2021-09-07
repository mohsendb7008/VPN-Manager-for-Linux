package ui

import Constants
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.v1.Dialog
import androidx.compose.ui.window.v1.DialogProperties
import config.VPNConfig
import kotlin.reflect.full.memberProperties

@Composable
fun ConfigDialog() {
    val configs = VPNConfig::class.sealedSubclasses
    Dialog(
        onDismissRequest = State::closeAddConfigDialog,
        DialogProperties(
            Constants.ADD_CONFIG_DIALOG_TITLE,
            size = IntSize(
                Constants.CONFIG_DIALOG_BASE_WIDTH * configs.size,
                Constants.CONFIG_DIALOG_BASE_HEIGHT
            )
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                configs.forEach { config ->
                    Row(modifier = Modifier.padding(8.dp).weight(1f)) {
                        RadioButton(State.chosenConfig.value == config,
                            onClick = {
                                State.chosenConfig.value = config
                            })
                        Text(text = config.simpleName.toString())
                    }
                }
            }
            State.chosenConfig.value.memberProperties.forEach {
                InputField(it.name, it.returnType, modifier = Modifier.padding(8.dp))
            }
        }
    }
}