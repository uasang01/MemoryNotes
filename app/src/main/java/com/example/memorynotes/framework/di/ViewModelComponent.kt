package com.example.memorynotes.framework.di

import com.example.memorynotes.framework.NoteListViewModel
import com.example.memorynotes.framework.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: NoteListViewModel)
}