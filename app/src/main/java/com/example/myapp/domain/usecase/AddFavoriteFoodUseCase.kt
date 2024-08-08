package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.PersonRepository

class AddFavoriteFoodUseCase(private val repository: PersonRepository) {
    suspend fun execute(personId: Long, foodId: Long) = repository.addFavoriteFood(personId, foodId)
}
