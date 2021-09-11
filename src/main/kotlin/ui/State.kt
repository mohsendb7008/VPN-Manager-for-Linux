package ui

import androidx.compose.runtime.mutableStateOf
import config.VPNConfig
import config.VPNConfigCompanion
import config.VPNConfigInformation
import kotlin.reflect.full.findAnnotation

object State {

    val showAddConfigDialog = mutableStateOf(false)

    fun openAddConfigDialog() {
        showAddConfigDialog.value = true
    }

    fun closeAddConfigDialog() {
        showAddConfigDialog.value = false
    }

    val chosenConfig = mutableStateOf(VPNConfig::class.sealedSubclasses.minByOrNull {
        it.findAnnotation<VPNConfigInformation>()?.priority ?: Int.MAX_VALUE
    }!!)

    val exceptionDialogThrowable = mutableStateOf<Throwable?>(null)

    fun reportException(throwable: Throwable) {
        exceptionDialogThrowable.value = throwable
    }

    fun clearException() {
        exceptionDialogThrowable.value = null
    }

    val allConfigs = mutableStateOf(loadConfigs())

    @OptIn(ExperimentalStdlibApi::class)
    fun loadConfigs() =
        buildList {
            VPNConfigCompanion::class.sealedSubclasses.forEach {
                it.objectInstance?.retrieveAll()?.let(this::addAll)
            }
        }

    fun reloadConfigs() {
        allConfigs.value = loadConfigs()
    }

}