package com.altaie.notes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.altaie.notes.R
import com.altaie.notes.databinding.FragmentHomeBinding
import com.altaie.notes.ui.base.BaseFragment
import com.altaie.notes.utils.observe
import kotlinx.coroutines.flow.collect


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by activityViewModels()
    override val inflate: (LayoutInflater, ViewGroup?, attachToRoot: Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            viewModel.navigateToEditNote.collect { it.observe(::navigateEditNote) }
        }

        binding.apply {
            notesRecycler.adapter = HomeAdapter(emptyList(), this@HomeFragment.viewModel)
            changeLayout.setOnClickListener { changeRecyclerLayout() }
            fabAdd.setOnClickListener { navigateNewNote() }
        }
    }

    private fun navigateEditNote(id: Long) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(noteId = id)
        findNavController().navigate(action)
        Log.v("TESTING", id.toString())
    }

    private fun navigateNewNote() {
        val action = HomeFragmentDirections.actionHomeFragmentToAddFragment()
        findNavController().navigate(action)
    }

    private fun changeRecyclerLayout() {
        binding.apply {
            if (notesRecycler.layoutManager is LinearLayoutManager) {
                changeLayout.setImageResource(R.drawable.ic_baseline_view_stream_24)
                notesRecycler.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            } else {
                changeLayout.setImageResource(R.drawable.ic_baseline_grid_view_24)
                notesRecycler.layoutManager = LinearLayoutManager(this@HomeFragment.context)
            }
        }
    }
}