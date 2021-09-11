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
import config.VPNConfigFieldInformation
import config.VPNConfigInformation
import util.PrimaryConstructorDefaultMapBuilder
import util.PrimaryConstructorMapValidator
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

@Composable
fun ConfigDialog() {
    val configs = VPNConfig::class.sealedSubclasses.sortedBy {
        it.findAnnotation<VPNConfigInformation>()?.priority ?: Int.MAX_VALUE
    }
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
                    Row(modifier = Modifier.padding(8.dp).weight(1f), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(State.chosenConfig.value == config,
                            onClick = {
                                State.chosenConfig.value = config
                            })
                        Text(
                            text = config.findAnnotation<VPNConfigInformation>()?.typeName
                                ?: config.simpleName.toString()
                        )
                    }
                }
            }
            val constructorMap = PrimaryConstructorDefaultMapBuilder.build(State.chosenConfig.value)
            State.chosenConfig.value.primaryConstructor?.parameters?.sortedBy {
                State.chosenConfig.value.declaredMemberProperties.first { member -> member.name == it.name }.getter.findAnnotation<VPNConfigFieldInformation>()?.priority
                    ?: Int.MAX_VALUE
            }?.forEach {
                InputField(
                    State.chosenConfig.value.declaredMemberProperties.first { member -> member.name == it.name }.getter.findAnnotation<VPNConfigFieldInformation>()?.fieldName
                        ?: it.name.toString(), it, constructorMap, modifier = Modifier.padding(8.dp)
                )
            }
            Button(onClick = {
                runCatching {
                    PrimaryConstructorMapValidator.validate(constructorMap)
                    State.chosenConfig.value.primaryConstructor?.callBy(constructorMap.mapValues { it.value.value })
                        ?.save()
                    State.reloadConfigs()
                }.onFailure {
                    State.reportException(it)
                }
                State.closeAddConfigDialog()
            }, modifier = Modifier.padding(8.dp)) {
                Text("Save")
            }
        }
    }
}