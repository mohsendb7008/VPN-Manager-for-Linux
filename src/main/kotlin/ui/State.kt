package ui

import androidx.compose.runtime.mutableStateOf
import config.VPNConfig

object State {

    val showAddConfigDialog = mutableStateOf(false)

    fun openAddConfigDialog() {
        showAddConfigDialog.value = true
    }

    fun closeAddConfigDialog() {
        showAddConfigDialog.value = false
    }

    val chosenConfig = mutableStateOf(VPNConfig::class.sealedSubclasses.first())

}