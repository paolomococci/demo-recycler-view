/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.demorecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import java.util.LinkedList

class WordListAdapter(context: Context, private val wordLinkedList: LinkedList<String>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    inner class WordViewHolder(itemView: View, private val wordListAdapter: WordListAdapter) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val wordItemView: TextView = itemView.findViewById(R.id.word)
        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(view: View) {
            val position = layoutPosition
            val element = wordLinkedList[position]
            wordLinkedList[position] = "Clicked! $element"
            wordListAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.word_list_item, parent, false
        )
        return WordViewHolder(itemView, this)
    }

    override fun onBindViewHolder(
        holder: WordViewHolder,
        position: Int
    ) {
        val current = wordLinkedList[position]
        holder.wordItemView.text = current
    }

    override fun getItemCount(): Int {
        return wordLinkedList.size
    }
}
