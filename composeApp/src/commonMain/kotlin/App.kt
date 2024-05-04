import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.room.RoomDatabase
import database.AppDatabase
import database.TodoEntity
import database.getRoomDatabase
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
@Preview
fun App(
    databaseBuilder: RoomDatabase.Builder<AppDatabase>,
) {
    val scope = rememberCoroutineScope()

    val database = remember { getRoomDatabase(databaseBuilder) }
    val dao = remember { database.getDao() }
    val items = dao.getAllAsFlow().collectAsState(initial = emptyList())
    var tappedItem by remember { mutableStateOf<TodoEntity?>(null) }

    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            body1 = MaterialTheme.typography.body1.copy(
                color = Color.White
            )
        )
    ) {
        var showContent by remember { mutableStateOf(false) }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Button(onClick = {
                    val item = TodoEntity(title = "item ${Random.nextInt()}", content = "content ${Random.nextInt()}")
                    scope.launch {
                        dao.insert(item)
                    }
                }) {
                    Text("Add item")
                }
                Spacer(Modifier.width(16.dp))

                Button(onClick = {
                    scope.launch {
                        tappedItem = null
                        dao.deleteAll()
                    }
                }) {
                    Text("Delete all")
                }
            }

            tappedItem?.run {
                Text("Tapped $title = $content")
            }
            Spacer(Modifier.height(32.dp))

            Text("Current Items (tap to show content):")
            LazyColumn {
                items(items.value.size) { item ->
                    Text(
                        items.value[item].title,
                        modifier = Modifier.clickable {
                            scope.launch {
                                tappedItem = dao.getById(items.value[item].id)
                            }
                        }
                    )
                }
            }
        }
    }
}
