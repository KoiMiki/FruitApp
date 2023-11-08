package com.koimiki.fruitapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.koimiki.fruitapp.R
import com.koimiki.fruitapp.model.Fruit
import com.koimiki.fruitapp.presenter.FruitPresenter


class FruitAdapter(private val fruitList: List<Fruit>, val fruitPresenter: FruitPresenter) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitAdapter.ViewHolder {
        val fruitItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_detail, parent, false)
        return ViewHolder(fruitItemView)
    }

    override fun onBindViewHolder(holder: FruitAdapter.ViewHolder, position: Int) {
        holder.tvItemFruit.text = fruitList[position].fruit
        holder.tvItemCalories.text = fruitList[position].calories.toString()
        holder.llFruitItem.setOnClickListener {
            val context = holder.itemView.context
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder
                .setIcon(R.drawable.fruit)
                .setTitle("Delete fruit from list")
                .setPositiveButton("Delete") { dialog, which ->
                    fruitPresenter.deleteFruit(fruitList[position])
                    true
                    Toast. makeText(context, "Fruit is deleted", Toast. LENGTH_SHORT). show()
                }
                .setNegativeButton("Cancel") { dialog, which ->

                }

            val dialog: AlertDialog = builder.create()
            dialog.show()


        }
    }


    override fun getItemCount(): Int {
        return fruitList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemFruit: TextView = itemView.findViewById(R.id.tvItemFruit)
        val tvItemCalories: TextView = itemView.findViewById(R.id.tvItemCalories)
        val llFruitItem: LinearLayout = itemView.findViewById(R.id.llFruitItem)
    }
}