package com.altaie.notes.ui.home

import androidx.lifecycle.viewModelScope
import com.altaie.notes.model.Repository
import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.ui.base.BaseViewModel
import com.altaie.notes.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(), HomeInteractionListener {
    private val _navigateToEditNote = MutableStateFlow<Event<Long>?>(null)
    val navigateToEditNote = _navigateToEditNote.asStateFlow()
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()
    val searchQuery = MutableStateFlow<String?>(null)

    init {
        loadNotes()
        search()
    }

    override fun onItemClicked(id: Long) {
        viewModelScope.launch { _navigateToEditNote.emit(Event(id)) }
    }

    override fun loadNotes() {
        collectValue(Repository.getAllNotes(), _notes)
    }

    private fun search() {
        stateFlowLauncher(searchQuery, ::executeSearch)
    }

    private fun executeSearch(query: String?) {
        when (query.isNullOrEmpty()) {
            true -> loadNotes()
            else -> collectValue(Repository.searchByTitleOrContent(query), _notes)
        }
    }
}