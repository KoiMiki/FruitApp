package com.koimiki.fruitapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.koimiki.fruitapp.model.Fruit

@Database(entities = [Fruit::class], version = 1, exportSchema = false)
abstract class FruitDatabase : RoomDatabase() {

    abstract fun fruitDao(): FruitDao

    companion object {
        @Volatile
        private var instance: FruitDatabase? = null

        fun getInstance(context: Context): FruitDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FruitDatabase {
            return Room.databaseBuilder(context, FruitDatabase::class.java, "fruit_database")
                .build()
        }
    }

}