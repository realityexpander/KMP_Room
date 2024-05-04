import androidx.compose.ui.window.ComposeUIViewController
import database.getDatabaseBuilderIos

fun MainViewController() =
    ComposeUIViewController {
        val databaseBuilder = getDatabaseBuilderIos()
        App(databaseBuilder)
    }
