package com.example.memorynotes.framework.di

import com.example.memorynotes.framework.UseCases
import com.memorynotes.core.repository.NoteRepository
import com.memorynotes.core.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}