import androidx.compose.ui.window.ComposeUIViewController
import database.getDatabaseBuilderIos

fun MainViewController() =  // IDE may say can delete this but it's required.
    ComposeUIViewController {
        val databaseBuilder = getDatabaseBuilderIos()

        App(databaseBuilder)
    }
