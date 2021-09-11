package ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import config.VPNConfig

@Composable
fun ConfigsList(configs: List<VPNConfig>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        configs.forEach {
            item {
                Text(text = it.toString(), modifier = Modifier.padding(8.dp))
                // TODO Later
            }
        }
    }
}