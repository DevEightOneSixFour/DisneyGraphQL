package com.superspecialapp.disneygraphql.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.superspecialapp.disneygraphql.data.model.DisneyItem
import com.superspecialapp.disneygraphql.databinding.CharacterListItemBinding

class CharacterAdapter(private val itemList: MutableList<DisneyItem> = mutableListOf())
    :RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun setDisneyItemList(list: List<DisneyItem>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: CharacterListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item: DisneyItem) {
                binding.tvCharacterItemName.text = item.name
                Glide.with(binding.ivCharacterItemImage)
                    .load(item.imageUrl)
                    .into(binding.ivCharacterItemImage)

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            CharacterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}