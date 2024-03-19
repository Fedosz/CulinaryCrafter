package com.example.culinarycrafter.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.culinarycrafter.R

class RecipeAdapter(private val listener: OnClickItemListener) :
    ListAdapter<RecipeEntity, RecipeAdapter.RecipeHolder>(
        RecipeComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        return RecipeHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        private val recipeTextView: TextView = itemView.findViewById(R.id.recipeTextView)


        fun bind(recipe: RecipeEntity, listener: OnClickItemListener) {
            recipeImageView.setImageResource(recipe.photo)
            recipeTextView.text = recipe.name
            itemView.setOnClickListener {
                listener.onClickItem(recipe)
            }
        }

        companion object {
            fun create(parent: ViewGroup): RecipeHolder {
                return RecipeHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.recipe_item, parent, false)
                )
            }
        }

    }

    class RecipeComparator : DiffUtil.ItemCallback<RecipeEntity>() {
        override fun areItemsTheSame(
            oldItem: RecipeEntity,
            newItem: RecipeEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecipeEntity,
            newItem: RecipeEntity
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    interface OnClickItemListener {

        fun onClickItem(recipe: RecipeEntity)

    }


}