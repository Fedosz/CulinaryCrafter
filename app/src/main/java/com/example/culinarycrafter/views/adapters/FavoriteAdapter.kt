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
import com.example.culinarycrafter.models.enums.RecipeEnum

class FavoriteAdapter(private val listener: OnClickItemListener) :
    ListAdapter<RecipeEnum, FavoriteAdapter.FavoriteHolder>(FavoriteComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        return FavoriteHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class FavoriteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        private val recipeTextView: TextView = itemView.findViewById(R.id.recipeTextView)
        private val deleteImageView: ImageView = itemView.findViewById(R.id.deleteImageView)

        fun bind(recipe: RecipeEnum, listener: OnClickItemListener) {
            recipeImageView.setImageResource(recipe.photo)
            recipeTextView.text = recipe.value
            deleteImageView.setOnClickListener {
                listener.onClickItem(recipe)
            }
        }

        companion object {
            fun create(parent: ViewGroup): FavoriteHolder {
                return FavoriteHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.recipe_item, parent, false)
                )
            }
        }

    }

    class FavoriteComparator : DiffUtil.ItemCallback<RecipeEnum>() {
        override fun areItemsTheSame(
            oldItem: RecipeEnum,
            newItem: RecipeEnum
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecipeEnum,
            newItem: RecipeEnum
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    interface OnClickItemListener {

        fun onClickItem(recipe: RecipeEnum)

    }


}