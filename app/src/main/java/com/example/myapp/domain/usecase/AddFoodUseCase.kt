package com.example.myapp.domain.usecase

import com.example.myapp.data.model.Food
import com.example.myapp.domain.repository.PersonRepository

class AddFoodUseCase(private val repository: PersonRepository) {
    suspend fun execute(food: Food) = repository.addFood(food)
}
