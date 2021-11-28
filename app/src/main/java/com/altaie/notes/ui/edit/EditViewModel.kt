package com.altaie.notes.ui.edit

import com.altaie.notes.R
import com.altaie.notes.model.Repository
import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.ui.base.BaseViewModel
import com.altaie.notes.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import java.util.*

class EditViewModel : BaseViewModel(), EditInteractionListener {
    val titleFlow = MutableStateFlow<String?>(null)
    val contentFlow = MutableStateFlow<String?>(null)
    val colorFlow = MutableStateFlow(R.color.white)
    val tagsFlow = MutableStateFlow<List<String>>(emptyList())
    val currentNote = MutableStateFlow<Note?>(null)
    val dateFlow = MutableStateFlow(Date())
    val deleteStatus = MutableStateFlow(Event(false))

    init {
        launcher { titleFlow.onCompletion { emitNote() } }
        launcher { contentFlow.onCompletion { emitNote() } }
    }

    private suspend fun emitNote() {
        currentNote.emit(
            currentNote.value?.apply {
                title = titleFlow.value ?: ""
                content = contentFlow.value.toString()
                tags = tagsFlow.value
                color = colorFlow.value
            }
        )
    }

    override fun saveNote(note: Note) = launcher { Repository.update(note) }

    override fun loadNote(noteID: Long) {
        collectValue(Repository.getNote(noteID), currentNote)
        stateFlowLauncher(currentNote, ::executeLoad)
    }

    private suspend fun executeLoad(note: Note?) = note?.let {
        titleFlow.emit(note.title)
        contentFlow.emit(note.content)
        colorFlow.emit(note.color)
        tagsFlow.emit(note.tags)
    }

    override fun deleteNote() = launcher {
        currentNote.value?.let { Repository.delete(it) }
        deleteStatus.emit(Event(true))
    }
}
