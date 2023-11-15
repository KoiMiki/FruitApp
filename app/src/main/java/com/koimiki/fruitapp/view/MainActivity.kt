package com.koimiki.fruitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koimiki.fruitapp.R
import com.koimiki.fruitapp.dao.FruitDatabase
import com.koimiki.fruitapp.model.Fruit
import com.koimiki.fruitapp.presenter.FruitPresenter
import com.koimiki.fruitapp.repository.FruitRepository

class MainActivity : AppCompatActivity(), FruitView {

    private lateinit var rvList: RecyclerView
    private lateinit var fruit: EditText
    private lateinit var calories: EditText
    private lateinit var fruitPresenter: FruitPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList = findViewById(R.id.rvList)
        fruit = findViewById(R.id.etFruit)
        calories = findViewById(R.id.etCalories)

        rvList.layoutManager = LinearLayoutManager(this)

        val fruitDao = FruitDatabase.getInstance(applicationContext).fruitDao()
        val fruitRepository = FruitRepository(fruitDao)

        fruitPresenter = FruitPresenter(this, fruitRepository)
        fruitPresenter.loadFruitList()

    }

    override fun displayFruitList(fruitList: List<Fruit>) {
        val adapter = FruitAdapter(fruitList, fruitPresenter)
        rvList.adapter = adapter
    }

    fun addtoFruitList(view: View) {
        if (isInputValid()) {
            fruitPresenter.addFruitToList(
                fruit.text.toString(),
                calories.text.toString()
            )
        }
    }

    private fun isInputValid(): Boolean {
        if (fruit.text.trim().isEmpty()) {
            fruit.error = "Fruit is required"
            return false
        }
        if (calories.text.trim().isEmpty()) {
            calories.error = "Calories is required"
            return false
        }
        return true
    }
}