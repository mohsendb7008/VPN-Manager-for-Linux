package util

import androidx.compose.runtime.MutableState
import kotlin.reflect.KParameter

object PrimaryConstructorMapValidator {

    fun validate(map: Map<KParameter, MutableState<Any?>>) {
        map.forEach { (kParameter, mutableState) ->
            when (kParameter.type.toString()) {
                "kotlin.String" -> {
                    if ((mutableState.value as String).trim() == "")
                        throw IllegalArgumentException("${kParameter.name} should not be empty!")
                }
                "kotlin.Int" -> {
                    if ((mutableState.value as Int?) == null)
                        throw IllegalArgumentException("${kParameter.name} should not be empty!")
                }
            }
        }
    }

}