package com.example.memorynotes.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.memorynotes.R
import com.example.memorynotes.databinding.FragmentNoteBinding
import com.example.memorynotes.framework.NoteViewModel
import com.memorynotes.core.data.Note

class NoteFragment : Fragment() {

    val binding get() = _binding!!
    private var _binding: FragmentNoteBinding? = null

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            noteViewModel.getNote(noteId)
        }

        binding.checkButton.setOnClickListener {
            if (binding.titleEditText.text.toString() != "" || binding.contentEditText.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = binding.titleEditText.text.toString()
                currentNote.content = binding.contentEditText.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }

                noteViewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(binding.checkButton).popBackStack()
            }
        }


        observeViewModel()
    }

    private fun observeViewModel() {
        noteViewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(binding.titleEditText).popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong, please try again!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        noteViewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                binding.titleEditText.setText(it.title, TextView.BufferType.EDITABLE)
                binding.contentEditText.setText(it.content, TextView.BufferType.EDITABLE)
            }
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.titleEditText.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_note -> {
                if(context != null && noteId != 0L)
                    AlertDialog.Builder(requireContext())
                        .setTitle("삭제")
                        .setMessage("정말 삭제하시겠습니까?")
                        .setPositiveButton("예") { dialogInterface, i ->
                            noteViewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("아니오") { dialogInterface, i ->

                        }
                        .create()
                        .show()
            }
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}