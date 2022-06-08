package com.example.petsshelter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PetRVAdapter(
    val context: Context,
    val petClickedInterface: MainActivity,
    val petClickedDeleteInterface: PetClickedDeleteInterface
) : RecyclerView.Adapter<PetRVAdapter.ViewHolder>(){

    private val allPet = ArrayList<Pet>()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val petName = itemView.findViewById<TextView>(R.id.name)
        val petBreed = itemView.findViewById<TextView>(R.id.breed)
        val petGender = itemView.findViewById<TextView>(R.id.gender)
        val petWeight = itemView.findViewById<TextView>(R.id.weight)
        val deleteIV = itemView.findViewById<ImageView>(R.id.delbutton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.petName.setText(allPet.get(position).petName)
        holder.petBreed.setText(allPet.get(position).petBreed)
        holder.petGender.setText(allPet.get(position).petGender)
        holder.petWeight.setText(allPet.get(position).petWeight)

        holder.deleteIV.setOnClickListener{
            petClickedDeleteInterface.onDeleteIconClicked(allPet.get(position))
        }

        holder.itemView.setOnClickListener{
            petClickedInterface.onPetClick(allPet.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allPet.size
    }

    fun update (newList : List<Pet>){
        allPet.clear()
        allPet.addAll(newList)
        notifyDataSetChanged()
    }
}

interface PetClickedDeleteInterface {
    fun onDeleteIconClicked(pet: Pet)
}

interface PetClickedInterface {
    fun onPetClick(pet: Pet)
}