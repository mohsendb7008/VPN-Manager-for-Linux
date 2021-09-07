package config

import kotlinx.serialization.Serializable

@Serializable
data class TwoFAVPNConfig(
    val name: String,
    val serverIP: String,
    val serverPort: Int,
    val autoOTP: Boolean = false,
    val username: String? = null,
    val password: String? = null
): VPNConfig