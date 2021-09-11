package config

import kotlinx.serialization.Serializable

@Serializable
@VPNConfigInformation("2FA VPN", 1)
data class TwoFAVPNConfig(
    @get:VPNConfigFieldInformation("Name", 1)
    val name: String,
    @get:VPNConfigFieldInformation("Server IP", 2)
    val serverIP: String,
    @get:VPNConfigFieldInformation("Server Port", 3)
    val serverPort: Int,
    @get:VPNConfigFieldInformation("Auto OTP", 4)
    val autoOTP: Boolean = false,
    @get:VPNConfigFieldInformation("Username", 5)
    val username: String? = null,
    @get:VPNConfigFieldInformation("Password", 6)
    val password: String? = null
) : VPNConfig