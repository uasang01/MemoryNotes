package com.example.memorynotes.framework.di

import android.app.Application
import com.example.memorynotes.framework.RoomNoteDataSource
import com.memorynotes.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule() {
    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}