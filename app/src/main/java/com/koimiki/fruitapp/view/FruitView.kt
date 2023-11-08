package com.koimiki.fruitapp.view

import com.koimiki.fruitapp.model.Fruit

interface FruitView {

    fun displayFruitList(fruitList: List<Fruit>)
}