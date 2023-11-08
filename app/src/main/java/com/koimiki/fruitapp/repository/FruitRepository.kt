package com.koimiki.fruitapp.repository

import com.koimiki.fruitapp.dao.FruitDao
import com.koimiki.fruitapp.model.Fruit

class FruitRepository(private val fruitDao: FruitDao) {

    fun getAll(): List<Fruit> {
        return fruitDao.getAllFruit()
    }

    fun insert(fruit: Fruit) {
        fruitDao. insertFruit(fruit)
    }

    fun delete(fruit: Fruit) {
        fruitDao. deleteFruit(fruit)
    }
}