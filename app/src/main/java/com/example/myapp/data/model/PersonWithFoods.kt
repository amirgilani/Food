package com.example.myapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity
data class PersonWithFoods(
    @Embedded val person: Person,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(PersonFoodCrossRef::class)
    )
    val foods: List<Food>
)
