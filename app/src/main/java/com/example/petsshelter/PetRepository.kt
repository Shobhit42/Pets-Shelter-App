package com.example.petsshelter

import androidx.lifecycle.LiveData

class PetRepository(private val petDao : PetDao) {
    val allPets : LiveData<List<Pet>> = petDao.getAllPets()

    suspend fun insert(pet : Pet){
        petDao.insert(pet)
    }

    suspend fun delete(pet : Pet){
        petDao.delete(pet)
    }

    suspend fun update(pet : Pet){
        petDao.update(pet)
    }
}