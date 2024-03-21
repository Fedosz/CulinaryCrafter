package com.example.culinarycrafter.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.culinarycrafter.models.entities.UserEntity
import com.example.culinarycrafter.models.daos.UserDao
import com.example.culinarycrafter.models.entities.RecipesIngredientEntity
import com.example.culinarycrafter.models.entities.UserIngredientEntity
import com.example.culinarycrafter.models.entities.UserRecipeEntity

@Database(
    entities = [
        UserEntity::class,
        RecipesIngredientEntity::class,
        UserRecipeEntity::class,
        UserIngredientEntity::class
    ],
    version = 1,
    exportSchema = false
)
public abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getDatabase(context: Context): RecipeRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeRoomDatabase::class.java,
                    "recipe_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }


}