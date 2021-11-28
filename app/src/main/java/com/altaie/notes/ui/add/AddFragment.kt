package com.altaie.notes.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.altaie.notes.databinding.FragmentAddBinding
import com.altaie.notes.ui.base.BaseFragment


class AddFragment : BaseFragment<FragmentAddBinding>() {
    override val viewModel: AddViewModel by activityViewModels()
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentAddBinding
        get() = FragmentAddBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goBack.setOnClickListener { findNavController().navigateUp() }
    }

    override fun onPause() {
        super.onPause()
        viewModel.run { currentNote.value?.run { if(this.content.isNotEmpty()) saveNote(this) } }

        binding.apply {
            title.text = null
            content.text = null
        }
    }
}
