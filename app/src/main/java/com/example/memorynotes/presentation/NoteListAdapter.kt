package com.example.memorynotes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorynotes.R
import com.example.memorynotes.databinding.ItemNoteBinding
import com.memorynotes.core.data.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(var notes: ArrayList<Note>, val action: ListAction) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNote(newNotes: List<Note>) {
        val prevSize = itemCount
        notes.clear()
        notes.addAll(newNotes)
        val currSize = itemCount
        notifyItemRangeChanged(0, if (prevSize > currSize) prevSize else currSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        ItemNoteBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size

    inner class NoteViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        private val layout = binding.noteLayout
        private val title = binding.title
        private val content = binding.content
        private val date = binding.date
        private val noteWords = binding.wordCount

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)
            date.text = "last updated : ${sdf.format(resultDate)}"
            noteWords.text = "글자수 : ${note.wordCount}"

            layout.setOnClickListener { action.onClick(note.id) }
        }
    }
}