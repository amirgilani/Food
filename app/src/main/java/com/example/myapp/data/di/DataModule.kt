package com.example.myapp.data.di

import android.content.Context
import com.example.myapp.data.local.dao.AppDao
import com.example.myapp.data.local.database.AppDatabase
import com.example.myapp.data.repository.PersonRepositoryImpl
import com.example.myapp.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAppDao(database: AppDatabase): AppDao {
        return database.appDao()
    }

    @Provides
    @Singleton
    fun providePersonRepository(dao: AppDao): PersonRepository {
        return PersonRepositoryImpl(dao)
    }
}
