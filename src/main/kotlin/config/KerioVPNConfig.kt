package config

import kotlinx.serialization.Serializable

@Serializable
data class KerioVPNConfig(
    val name: String,
    val serverIP: String,
    val serverPort: Int,
    val fingerPrint: String,
    val username: String? = null,
    val password: String? = null
): VPNConfig