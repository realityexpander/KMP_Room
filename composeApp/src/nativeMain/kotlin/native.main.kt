import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory


//fun getDatabaseBuilderIos(): RoomDatabase.Builder<AppDatabase> {
//    val dbFilePath = NSHomeDirectory() + "/my_room.db"
////    return Room.databaseBuilder<AppDatabase>(
////        name = dbFilePath,
////        factory =  { AppDatabase::class.instantiateImpl() }
////    )
//    val x = Room.databaseBuilder<AppDatabase>(
//        name = dbFilePath,
//        factory =  { AppDatabase::class.instantiateImpl() }
//    )
//    x.setDriver(BundledSQLiteDriver())
//        .setQueryCoroutineContext(Dispatchers.IO)
//        .build()
//
//    return x
//}
