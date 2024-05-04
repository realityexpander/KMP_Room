package database

import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import androidx.sqlite.execSQL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun getDao(): TodoDao
}

@Entity
data class TodoEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String,
  val content: String
)

@Dao
interface TodoDao {
  @Insert
  suspend fun insert(item: TodoEntity)

  @Query("DELETE FROM TodoEntity")
  suspend fun deleteAll()

  @Query("SELECT count(*) FROM TodoEntity")
  suspend fun count(): Int

  @Query("SELECT * FROM TodoEntity")
  fun getAllAsFlow(): Flow<List<TodoEntity>>
}

fun getRoomDatabase(
  builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {

  return builder
    .fallbackToDestructiveMigrationOnDowngrade(true)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
}
