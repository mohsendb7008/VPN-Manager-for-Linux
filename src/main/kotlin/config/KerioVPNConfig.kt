package config

import kotlinx.serialization.Serializable

@Serializable
@VPNConfigInformation("Kerio VPN", 2)
data class KerioVPNConfig(
    @get:VPNConfigFieldInformation("Name", 1)
    val name: String,
    @get:VPNConfigFieldInformation("Server IP", 2)
    val serverIP: String,
    @get:VPNConfigFieldInformation("Server Port", 3)
    val serverPort: Int,
    @get:VPNConfigFieldInformation("Fingerprint", 4)
    val fingerPrint: String,
    @get:VPNConfigFieldInformation("Username", 5)
    val username: String? = null,
    @get:VPNConfigFieldInformation("Password", 6)
    val password: String? = null
) : VPNConfig