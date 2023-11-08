package com.koimiki.fruitapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.koimiki.fruitapp.model.Fruit

@Dao
interface FruitDao {
    @Query("SELECT * FROM Fruit")
    fun getAllFruit(): List<Fruit>

    @Insert
    fun insertFruit(fruit: Fruit)

    @Delete
    fun deleteFruit(fruit: Fruit)

}