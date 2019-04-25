/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.demorecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {

    private val wordLinkedList = LinkedList<String>()
    private var recyclerView: RecyclerView? = null
    private var wordListAdapter: WordListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.floating_action_button)
        floatingActionButton.setOnClickListener {
            val wordListSize = wordLinkedList.size
            /* add a new word to the wordList */
            wordLinkedList.addLast("some more phrase $wordListSize")
            /* notify the adapter, that the data has changed */
            recyclerView!!.adapter!!.notifyItemInserted(wordListSize)
            /* scroll to the bottom */
            recyclerView!!.smoothScrollToPosition(wordListSize)
        }

        populate()

        /* create recycler view */
        recyclerView = findViewById(R.id.recycler_view)
        /* create an adapter and supply the data to be displayed */
        wordListAdapter = WordListAdapter(this, wordLinkedList)
        /* connect the adapter with the recycler view */
        recyclerView!!.adapter = wordListAdapter
        /* give the recycler view a default layout manager */
        recyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        /* inflate the menu; this adds items to the action bar if it is present */
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun populate() {
        wordLinkedList.run {
            addLast("let's start by writing a simple phrase")
        }
    }
}
