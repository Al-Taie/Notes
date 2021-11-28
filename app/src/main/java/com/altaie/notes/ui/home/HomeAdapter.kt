package com.altaie.notes.ui.home

import com.altaie.notes.R
import com.altaie.notes.model.data.doamin.Note
import com.altaie.notes.ui.base.BaseAdapter

class HomeAdapter(items: List<Note>, listener: HomeInteractionListener) :
    BaseAdapter<Note>(_items = items, _listener = listener) {
    override val layoutID = R.layout.item_note
}