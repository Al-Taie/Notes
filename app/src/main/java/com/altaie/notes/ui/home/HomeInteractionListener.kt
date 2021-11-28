package com.altaie.notes.ui.home

import com.altaie.notes.ui.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener{
    fun onItemClicked(id: Long)
    fun loadNotes()
}
