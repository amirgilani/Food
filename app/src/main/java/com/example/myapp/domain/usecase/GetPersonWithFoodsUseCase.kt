package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.PersonRepository

class GetPersonWithFoodsUseCase(private val repository: PersonRepository) {
    suspend fun execute(personId: Long) = repository.getPersonWithFoods(personId)
}
