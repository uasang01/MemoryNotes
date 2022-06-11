package com.example.memorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.memorynotes.core.data.Note
import com.memorynotes.core.repository.NoteRepository
import com.memorynotes.core.usecase.AddNote
import com.memorynotes.core.usecase.GetAllNotes
import com.memorynotes.core.usecase.GetNote
import com.memorynotes.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val noteRepository = NoteRepository(RoomNoteDataSource(application))

    val useCases= UseCases(
        AddNote(noteRepository),
        GetNote(noteRepository),
        GetAllNotes(noteRepository),
        RemoveNote(noteRepository)
    )

    val notes = MutableLiveData<List<Note>>()

    fun getNotes(){
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()
            notes.postValue(noteList)
        }
    }
}