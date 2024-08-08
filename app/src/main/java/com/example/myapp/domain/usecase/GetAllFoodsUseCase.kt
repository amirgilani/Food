package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.PersonRepository

class GetAllFoodsUseCase(private val repository: PersonRepository) {
    suspend fun execute() = repository.getAllFoods()
}
