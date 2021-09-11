package util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor

object PrimaryConstructorDefaultMapBuilder {

    fun build(`class`: KClass<*>) = HashMap<KParameter, MutableState<Any?>>().also {
        `class`.primaryConstructor?.parameters?.forEach { param ->
            it[param] = when (param.type.toString()) {
                "kotlin.String" -> mutableStateOf("")
                "kotlin.String?" -> mutableStateOf("")
                "kotlin.Int" -> mutableStateOf(null)
                "kotlin.Boolean" -> mutableStateOf(false)
                else -> throw NotImplementedError("${param.type} is not supported")
            }
        }
    }

}