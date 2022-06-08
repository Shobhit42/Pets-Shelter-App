package com.example.petsshelter

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "petsTable")
class Pet(
    @ColumnInfo(name = "name") val petName: String,
    @ColumnInfo(name = "breed") val petBreed: String,
    @ColumnInfo(name = "gender") val petGender: String,
    @ColumnInfo(name = "weight") val petWeight: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}