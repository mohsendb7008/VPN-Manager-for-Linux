import androidx.compose.desktop.Window
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.IntSize
import ui.AppTheme
import ui.ConfigDialog
import ui.ExceptionDialog
import ui.State

fun main() = Window(title = Constants.APP_NAME, size = IntSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT)) {
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

        }
        if (State.exceptionDialogThrowable.value != null)
            ExceptionDialog(State.exceptionDialogThrowable.value!!)
        if (State.showAddConfigDialog.value)
            ConfigDialog()
    }
}