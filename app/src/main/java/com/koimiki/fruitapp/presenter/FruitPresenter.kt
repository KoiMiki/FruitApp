package com.koimiki.fruitapp.presenter

import com.koimiki.fruitapp.model.Fruit
import com.koimiki.fruitapp.repository.FruitRepository
import com.koimiki.fruitapp.view.FruitView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FruitPresenter(
    private val fruitView: FruitView,
    private val fruitRepository: FruitRepository
) {

    fun loadFruitList() {
        Thread {
            val fruitList = fruitRepository.getAll()
            fruitView.displayFruitList(fruitList)
        }.start()
    }

    fun deleteFruit(fruit: Fruit) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                fruitRepository.delete(fruit)
                val fruitList = fruitRepository.getAll()
                withContext(Dispatchers.Main) {
                    fruitView.displayFruitList(fruitList)
                }
            }
        }
    }

    fun addFruitToList(fruit: String, calories: String) {
        val newFruit = Fruit(fruit = fruit, calories = calories)
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                fruitRepository.insert(newFruit)
                val fruitList = fruitRepository.getAll()
                withContext(Dispatchers.Main) {
                    fruitView.displayFruitList(fruitList)
                }
            }
        }
    }
}