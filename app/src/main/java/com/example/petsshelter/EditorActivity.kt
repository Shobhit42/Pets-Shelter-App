package com.example.petsshelter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class EditorActivity : AppCompatActivity() {
    lateinit var petNameEdit: EditText
    lateinit var petBreedEdit: EditText
    lateinit var petGenderEdit: EditText
    lateinit var petWeightEdit: EditText
    lateinit var addUpdateButton : Button
    lateinit var viewModel: PetViewModel
    var petID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor_activity)
        petNameEdit = findViewById(R.id.edit_pet_name)
        petBreedEdit = findViewById(R.id.edit_pet_breed)
        petGenderEdit = findViewById(R.id.edit_pet_gender)
        petWeightEdit = findViewById(R.id.edit_pet_weight)
        addUpdateButton= findViewById(R.id.button1)

        viewModel = ViewModelProvider(this , ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PetViewModel::class.java)

        val petType = intent.getStringExtra("petType")
        if(petType.equals("Edit")){
            val petName = intent.getStringExtra("petName")
            val petBreed = intent.getStringExtra("petBreed")
            val petGender = intent.getStringExtra("petGender")
            val petWeight = intent.getStringExtra("petWeight")
            petID = intent.getIntExtra("petID" , -1)
            addUpdateButton.setText("Update Pet")
            petNameEdit.setText(petName)
            petBreedEdit.setText(petBreed)
            petGenderEdit.setText(petGender)
            petWeightEdit.setText(petWeight)
        }else{
            addUpdateButton.setText("Save Pet")
        }

        addUpdateButton.setOnClickListener{
            val petName = petNameEdit.text.toString()
            val petBreed = petBreedEdit.text.toString()
            val petGender = petGenderEdit.text.toString()
            val petWeight = petWeightEdit.text.toString()

            if(petType.equals("Edit")){
                if(petName.isNotEmpty() && petBreed.isNotEmpty() && petGender.isNotEmpty() && petWeight.isNotEmpty()){
                    val updatePet = Pet(petName ,petBreed,petGender, petWeight)
                    updatePet.id = petID
                    viewModel.updatePet(updatePet)
                    Toast.makeText(this , "Pet Updated" , Toast.LENGTH_LONG).show()
                }
            }else{
                if(petName.isNotEmpty() && petBreed.isNotEmpty() && petGender.isNotEmpty() && petWeight.isNotEmpty()){
                    viewModel.insertPet(Pet(petName ,petBreed,petGender, petWeight))
                    Toast.makeText(this , "New Pet Added" , Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}