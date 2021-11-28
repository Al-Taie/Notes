package com.altaie.notes.model.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.altaie.notes.model.data.doamin.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DB_NAME = "DB_NOTE"
        @Volatile private var instance: NoteDatabase? = null

        fun getInstance(context: Context) : NoteDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        val getInstance get() = instance!!

        private fun buildDatabase(context: Context) : NoteDatabase {
            return Room.databaseBuilder(context, NoteDatabase::class.java, DB_NAME).build()
        }
    }
}
