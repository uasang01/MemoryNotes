package com.example.memorynotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memorynotes.databinding.FragmentListBinding
import com.example.memorynotes.framework.NoteViewModel

class ListFragment : Fragment(), ListAction {


    val binding get() = _binding!!
    private var _binding: FragmentListBinding? = null

    private lateinit var noteViewModel: NoteViewModel

    private val noteListAdapter by lazy { NoteListAdapter(arrayListOf(), this)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteListAdapter
        }
        binding.addNoteButton.setOnClickListener { goToNoteDetails() }

        noteViewModel = ViewModelProviders.of(this@ListFragment).get(NoteViewModel::class.java)

        observeViewModels()
    }

    private fun observeViewModels() {
        noteViewModel.notes.observe(viewLifecycleOwner) { noteList ->
            binding.loadingView.visibility = View.GONE
            binding.notesListView.visibility = View.VISIBLE
            noteListAdapter.updateNote(noteList.sortedByDescending { it.updateTime })
            Log.e("ListFragment", "$noteList")
        }
    }

    override fun onResume() {
        super.onResume()
        noteViewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.notesListView).navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}