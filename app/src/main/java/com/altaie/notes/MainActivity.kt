package com.altaie.notes

import android.os.Bundle
import com.altaie.notes.databinding.ActivityMainBinding
import com.altaie.notes.model.data.database.NoteDatabase
import com.altaie.notes.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val theme = R.style.Theme_Notes
    override val viewID = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NoteDatabase.getInstance(applicationContext)
    }
}