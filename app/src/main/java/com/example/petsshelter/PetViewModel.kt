package com.example.petsshelter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {
    val allPet: LiveData<List<Pet>>
    val repository: PetRepository

    init{
        val dao = PetDatabase.getDatabase(application).getPetDao()
        repository = PetRepository(dao)
        allPet = repository.allPets
    }

    fun deletePet(pet : Pet) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(pet)
    }

    fun insertPet(pet : Pet) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(pet)
    }

    fun updatePet(pet : Pet) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(pet)
    }
}