package config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

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
) : VPNConfig {

    override fun save() = with(File(FOLDER, System.currentTimeMillis().toString())) {
        writeText(Yaml.default.encodeToString(serializer(), this@TwoFAVPNConfig))
    }

    companion object: VPNConfigCompanion {

        val FOLDER = File(Constants.CONFIGS_FOLDER, "2fa").also {
            if (!it.exists())
                it.mkdir()
        }

        override fun retrieveAll() = with(FOLDER) {
            listFiles()?.map { Yaml.default.decodeFromStream(serializer(), it.inputStream()) } ?: emptyList()
        }

    }

}