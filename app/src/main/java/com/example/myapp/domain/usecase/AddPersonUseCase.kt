package com.example.myapp.domain.usecase

import com.example.myapp.domain.repository.PersonRepository

class AddPersonUseCase(private val repository: PersonRepository) {
    suspend fun execute(person: com.example.myapp.data.model.Person) = repository.addPerson(person)
}
