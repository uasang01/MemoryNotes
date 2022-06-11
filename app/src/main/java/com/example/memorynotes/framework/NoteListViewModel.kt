//package com.example.memorynotes.framework
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import com.memorynotes.core.repository.NoteRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//
//class NoteListViewModel(application: Application): AndroidViewModel(application) {
//    private val coroutineScope = CoroutineScope(Dispatchers.IO)
//    private val noteRepository = NoteRepository(RoomNoteDataSource(application))
//
//    private val useCases = UseCases()
//}