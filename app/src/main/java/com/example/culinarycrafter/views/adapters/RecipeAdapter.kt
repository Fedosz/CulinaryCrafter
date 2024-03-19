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
import com.example.culinarycrafter.models.entities.RecipePercentEntity
import com.example.culinarycrafter.models.enums.RecipeEnum

class RecipeAdapter(private val listener: OnClickItemListener) :
    ListAdapter<RecipePercentEntity, RecipeAdapter.RecipeHolder>(
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
        private val deleteImageView: ImageView = itemView.findViewById(R.id.deleteImageView)


        fun bind(recipe: RecipePercentEntity, listener: OnClickItemListener) {
            recipeImageView.setImageResource(recipe.recipe.photo)
            recipeTextView.text = "${recipe.recipe.value} (${recipe.percent}%)"
            deleteImageView.visibility = View.GONE
            itemView.setOnClickListener {
                listener.onClickItem(recipe.recipe)
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

    class RecipeComparator : DiffUtil.ItemCallback<RecipePercentEntity>() {
        override fun areItemsTheSame(
            oldItem: RecipePercentEntity,
            newItem: RecipePercentEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecipePercentEntity,
            newItem: RecipePercentEntity
        ): Boolean {
            return oldItem.recipe.name == newItem.recipe.name
        }

    }

    interface OnClickItemListener {

        fun onClickItem(recipe: RecipeEnum)

    }


}