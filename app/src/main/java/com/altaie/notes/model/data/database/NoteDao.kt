package com.altaie.notes.model.data.database


import androidx.room.*
import com.altaie.notes.model.data.doamin.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM TB_NOTES ORDER BY date DESC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM TB_NOTES WHERE id = :noteID")
    fun getNotes(noteID: Long): Flow<Note?>

    @Query("SELECT * FROM TB_NOTES WHERE (title LIKE :query || '%' OR content LIKE :query || '%')")
    fun searchByTitleOrContent(query: String) : Flow<List<Note>>
}