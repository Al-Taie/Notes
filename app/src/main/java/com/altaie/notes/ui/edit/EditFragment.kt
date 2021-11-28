package com.altaie.notes.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.altaie.notes.databinding.FragmentEditBinding
import com.altaie.notes.ui.base.BaseFragment
import com.altaie.notes.utils.observe
import kotlinx.coroutines.flow.collect

class EditFragment : BaseFragment<FragmentEditBinding>() {
    private val args: EditFragmentArgs by navArgs()
    override val viewModel: EditViewModel by activityViewModels()
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentEditBinding
        get() = FragmentEditBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadNote(args.noteId)
        binding.goBack.setOnClickListener { findNavController().navigateUp() }

        lifecycleScope.launchWhenCreated {
            viewModel.deleteStatus.collect { it.observe { findNavController().navigateUp() } }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.run { currentNote.value?.run { saveNote(this) } }
    }
}
