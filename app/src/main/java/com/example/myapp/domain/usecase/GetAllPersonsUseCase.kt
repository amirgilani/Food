package com.example.domain.usecase

import com.example.myapp.domain.model.Person
import com.example.myapp.domain.repository.PersonRepository

class GetAllPersonsUseCase(private val repository: PersonRepository) {
    suspend fun execute(): List<com.example.myapp.data.model.Person> {
        return repository.getAllPersons()
    }
}
