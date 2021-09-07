import androidx.compose.desktop.Window
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.IntSize
import ui.AppTheme

fun main() = Window(title = Constants.APP_NAME, size = IntSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT)) {
    AppTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(Constants.APP_NAME)
            }, actions = {
                IconButton(onClick = {

                }) {
                    Icon(Icons.Filled.Add, null)
                }
            })
        }) {

        }
    }
}