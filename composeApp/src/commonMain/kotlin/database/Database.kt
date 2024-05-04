package database

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

@Dao
interface TodoDao {
  @Insert
  suspend fun insert(item: TodoEntity)

  // Delete all
  @Query("DELETE FROM TodoEntity")
  suspend fun deleteAll()

  @Query("SELECT count(*) FROM TodoEntity")
  suspend fun count(): Int

  @Query("SELECT * FROM TodoEntity")
  fun getAllAsFlow(): Flow<List<TodoEntity>>
}

@Entity
data class TodoEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val title: String,
  val content: String
)

fun getRoomDatabase(
  builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {

  class Migration1To2 : Migration(1, 2) {
    override fun migrate(connection: SQLiteConnection) {
      connection.execSQL("ALTER TABLE TodoEntity ADD COLUMN content TEXT NOT NULL DEFAULT ''")
    }
  }

  return builder
    .addMigrations(Migration1To2())
    .fallbackToDestructiveMigrationOnDowngrade(true)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
}

