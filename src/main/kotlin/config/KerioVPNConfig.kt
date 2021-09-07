package config

class KerioVPNConfig(
    name: String,
    serverIP: String,
    serverPort: Int,
    val fingerPrint: String,
    username: String? = null,
    password: String? = null
) : VPNConfig(
    name,
    serverIP,
    serverPort,
    username,
    password
)