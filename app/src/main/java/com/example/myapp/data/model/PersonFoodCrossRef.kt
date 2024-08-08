package com.example.myapp.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["personId", "foodId"])
data class PersonFoodCrossRef(
    val personId: Long,
    val foodId: Long
)
