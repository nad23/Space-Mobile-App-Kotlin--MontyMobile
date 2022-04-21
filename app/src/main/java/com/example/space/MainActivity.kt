package com.example.space

import android.app.SearchManager
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var badgeCounter: TextView? = null
    var pendingNotification = 2

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(ContentValues.TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.my_menu, menu)
        //   associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager  //to get a reference
        val searchMenuItem = menu.findItem(R.id.action_search) // to get a reference
        val searchView: SearchView = searchMenuItem.actionView as SearchView   //to get a reference
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchMenuItem.collapseActionView()
                Toast.makeText(this@MainActivity, "Looking for $query", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@MainActivity, "Looking for $newText", Toast.LENGTH_LONG).show()
                return true
            }
        })





        val notificationMenuItem= menu?.findItem(R.id.nav_notification)

        if(pendingNotification==0)
        {notificationMenuItem.setActionView(null)}
        else{
            notificationMenuItem.setActionView(R.layout.notification_badge)
            val view: View = notificationMenuItem.getActionView()
            badgeCounter = view?.findViewById(R.id.badge_counter)
            badgeCounter?.setText(pendingNotification.toString())
        }
        return true
    }


}