package com.example.petsshelter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), PetClickedDeleteInterface,PetClickedInterface {
    lateinit var petRV : RecyclerView
    lateinit var addFab : FloatingActionButton
    lateinit var viewModel: PetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        petRV = findViewById(R.id.PetRV)
        addFab = findViewById<FloatingActionButton>(R.id.fab)
        petRV.layoutManager = LinearLayoutManager(this)

        val petRVAdapter = PetRVAdapter(this , this , this)
        petRV.adapter = petRVAdapter
        viewModel = ViewModelProvider(this ,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(PetViewModel::class.java)
        viewModel.allPet.observe(this, Observer {list ->
            list?.let{
                petRVAdapter.update(it)
            }
        })

        addFab.setOnClickListener{
            val intent = Intent(this@MainActivity , EditorActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClicked(pet: Pet) {
        viewModel.deletePet(pet)
        Toast.makeText(this,"${pet.petName} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onPetClick(pet: Pet) {
        val intent = Intent(this@MainActivity , EditorActivity::class.java)
        intent.putExtra("petType" , "Edit")
        intent.putExtra("petName" , pet.petName)
        intent.putExtra("petBreed" , pet.petBreed)
        intent.putExtra("petGender" , pet.petGender)
        intent.putExtra("petWeight" , pet.petWeight)
        intent.putExtra("petID" , pet.id)
        startActivity(intent)
        this.finish()
    }
}