package com.mj.caloriecounter.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mj.caloriecounter.model.ConsumedFood
import com.mj.caloriecounter.model.FinishedExercise

@Database(entities = [ConsumedFood::class, FinishedExercise::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun caloriesDao(): CaloriesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "calories_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}