package com.example.memorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.memorynotes.framework.di.ApplicationModule
import com.example.memorynotes.framework.di.DaggerViewModelComponent
import com.memorynotes.core.data.Note
import com.memorynotes.core.repository.NoteRepository
import com.memorynotes.core.usecase.AddNote
import com.memorynotes.core.usecase.GetAllNotes
import com.memorynotes.core.usecase.GetNote
import com.memorynotes.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    @Inject
    lateinit var useCases: UseCases

    init{
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes(){
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()
            noteList.forEach { it.wordCount = useCases.getWordCount.invoke(it) }
            notes.postValue(noteList)
        }
    }
}