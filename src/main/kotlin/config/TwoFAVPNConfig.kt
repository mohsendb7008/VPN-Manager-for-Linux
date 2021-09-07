package config

class TwoFAVPNConfig(
    name: String,
    serverIP: String,
    serverPort: Int,
    var autoOTP: Boolean = false,
    username: String? = null,
    password: String? = null
) : VPNConfig(
    name,
    serverIP,
    serverPort,
    username,
    password
)