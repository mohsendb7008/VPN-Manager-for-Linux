package config

sealed class VPNConfig(
    val name: String,
    val serverIP: String,
    val serverPort: Int,
    var username: String? = null,
    var password: String? = null
)