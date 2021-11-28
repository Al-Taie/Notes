package com.altaie.notes.model.data.doamin

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "TB_NOTES")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String = "",
    var content: String = "",
    var tags: List<String> = emptyList(),
    var color: Int = 0,
    var date: Date = Date()
)
