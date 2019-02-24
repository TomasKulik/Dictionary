package com.example.dictionary

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.word_row.view.*

class MainAdapter(val results: Results): RecyclerView.Adapter<CustomViewHolder>(){

    // number of times
    override fun getItemCount(): Int {
        return results.data.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val celForRow = layoutInflater.inflate(R.layout.word_row, parent, false)
        return CustomViewHolder(celForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = results.data.get(position)
        holder.view.textView_kanji.text = data.slug

		for (english in data.senses){
			println(english.english_definitions.size)
			val count = english.english_definitions.size

			for (i in english.english_definitions) {
				holder.view.textView_english_translation.append("$i, ")
			}

//			for (english in data.senses){
//				println(english.english_definitions.size)
//				val count = english.english_definitions.size
//			var j = 0
//
//				for (i in english.english_definitions)
//				{
//					if(j < count - 1)
//					{
//						holder.view.textView_english_translation.append("$i, ")
//						j += 1
//					}
//					else
//					holder.view.textView_english_translation.append(i + "\n")
//				}
//			}
		}
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}