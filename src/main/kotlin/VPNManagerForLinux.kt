import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import ui.*

fun main() {
    Window(title = Constants.APP_NAME, size = IntSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT)) {
        AppTheme {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Text(Constants.APP_NAME)
                }, actions = {
                    IconButton(onClick = State::openAddConfigDialog) {
                        Icon(Icons.Filled.Add, null)
                    }
                })
            }) {
                ConfigsList(State.allConfigs.value, Modifier.padding(it))
            }
            if (State.exceptionDialogThrowable.value != null)
                ExceptionDialog(State.exceptionDialogThrowable.value!!)
            if (State.showAddConfigDialog.value)
                ConfigDialog()
        }
    }
}