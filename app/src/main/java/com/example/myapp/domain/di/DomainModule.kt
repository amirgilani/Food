package com.example.myapp.domain.di

import com.example.domain.usecase.GetAllPersonsUseCase
import com.example.myapp.domain.repository.PersonRepository
import com.example.myapp.domain.usecase.AddFavoriteFoodUseCase
import com.example.myapp.domain.usecase.AddFoodUseCase
import com.example.myapp.domain.usecase.AddPersonUseCase
import com.example.myapp.domain.usecase.GetAllFoodsUseCase
import com.example.myapp.domain.usecase.GetPersonWithFoodsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideAddPersonUseCase(repository: PersonRepository): AddPersonUseCase {
        return AddPersonUseCase(repository)
    }

    @Provides
    fun provideAddFoodUseCase(repository: PersonRepository): AddFoodUseCase {
        return AddFoodUseCase(repository)
    }

    @Provides
    fun provideAddFavoriteFoodUseCase(repository: PersonRepository): AddFavoriteFoodUseCase {
        return AddFavoriteFoodUseCase(repository)
    }

    @Provides
    fun provideGetAllFoodsUseCase(repository: PersonRepository): GetAllFoodsUseCase {
        return GetAllFoodsUseCase(repository)
    }

    @Provides
    fun provideGetPersonWithFoodsUseCase(repository: PersonRepository): GetPersonWithFoodsUseCase {
        return GetPersonWithFoodsUseCase(repository)
    }

    @Provides
    fun provideGetAllPersonsUseCase(repository: PersonRepository): GetAllPersonsUseCase { // اضافه شده
        return GetAllPersonsUseCase(repository)
    }
}
