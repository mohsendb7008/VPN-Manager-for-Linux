package ui

import Constants
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.v1.Dialog
import androidx.compose.ui.window.v1.DialogProperties
import config.VPNConfig

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
        Row(modifier = Modifier.fillMaxWidth()) {
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
    }
}