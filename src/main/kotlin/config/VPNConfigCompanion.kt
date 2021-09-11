package config

sealed interface VPNConfigCompanion {

    fun retrieveAll(): List<VPNConfig>

}