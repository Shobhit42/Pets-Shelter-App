package com.example.petsshelter

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pet : Pet)

   @Update
   suspend fun update(pet : Pet)

   @Delete
   suspend fun delete(pet : Pet)

   @Query("Select * from petsTable order by id ASC")
   fun getAllPets() : LiveData<List<Pet>>

}