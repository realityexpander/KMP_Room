import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.room.RoomDatabase
import database.AppDatabase
import database.TodoEntity
import database.getRoomDatabase
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
@Preview
fun App(
    databaseBuilder: RoomDatabase.Builder<AppDatabase>
) {
    val scope = rememberCoroutineScope()
    val database = remember { getRoomDatabase(databaseBuilder) }
    val dao = remember { database.getDao() }
    val items = dao.getAllAsFlow().collectAsState(initial = emptyList())

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(16.dp)) {
                Button(onClick = {
                    val item = TodoEntity(title= "item ${Random.nextInt()}", content= "content")
                    scope.launch {
                        dao.insert(item)
                    }
                }) {
                    Text("Add item")
                }

                Button(onClick = {
                    scope.launch {
                        dao.deleteAll()
                    }
                }) {
                    Text("Delete all")
                }
            }

            LazyColumn {
                items(items.value.size) { item ->
                    Text(items.value[item].title)
                }
            }
        }
    }
}

//@Composable
//fun FruittieItem(
//    item: Fruittie,
//    onAddToCart: (fruittie: Fruittie) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .clip(RoundedCornerShape(8.dp)),
//        shape = RoundedCornerShape(8.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface,
//        ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 8.dp,
//        ),
//    ) {
//        Column {
//            Text(
//                text = item.name,
//                color = MaterialTheme.colorScheme.onBackground,
//                style = MaterialTheme.typography.titleMedium,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 8.dp),
//            )
//            Row {
//                Text(
//                    text = item.fullName,
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp)
//                        .padding(bottom = 8.dp),
//                    color = MaterialTheme.colorScheme.onSurface,
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                )
//                Spacer(modifier = Modifier.padding(vertical = 4.dp))
//                Box(
//                    modifier = Modifier
//                        .height(50.dp)
//                        .fillMaxWidth(),
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .padding(horizontal = 16.dp, vertical = 8.dp)
//                            .align(Alignment.TopEnd),
//                        verticalAlignment = Alignment.CenterVertically,
//                    ) {
//                        Button(onClick = { onAddToCart(item) }) {
//                            Text(stringResource(R.string.add))
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun CardDetailsView(cart: List<CartItemDetails>, modifier: Modifier = Modifier) {
//    Column(
//        modifier.padding(horizontal = 32.dp)
//    ) {
//        cart.forEach { item ->
//            Text(text = "${item.fruittie.name} : ${item.count}")
//        }
//    }
//}
//
//@Preview
//@Composable
//fun ItemPreview() {
//    FruittieItem(
//        Fruittie(name = "Fruit", fullName = "Fruitus Mangorus", calories = "240"),
//        onAddToCart = {},
//    )
//}


//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
