package com.example.dnd_list_2

import android.content.Context
import android.content.Intent
import android.hardware.SensorManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.dnd_list_2.databinding.ActivityMainBinding


var currentFrame = 0

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var menuBarToggle: ActionBarDrawerToggle
    private var listFragment = ListFragment()
    val loginFragment = LoginFragment()

    val sensorManager: SensorManager
        get() = getSystemService(Context.SENSOR_SERVICE) as SensorManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setupTodoListFragment()
        setupMenuDrawer()

        setContentView(binding.root)
        switchTo(loginFragment)
    }


    private fun setupTodoListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frmListContainer, listFragment)
            commit()
        }
    }

    private fun setupMenuDrawer() {
        menuBarToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.menu_open,
            R.string.menu_close
        )

        binding.drawerLayout.addDrawerListener(menuBarToggle)
        menuBarToggle.syncState()

        // when the menu drawer opens, the toggle button moves to a "back" button and it will close again.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // handle menu drawer item clicks.
        // since these are all events that influence the fragment list, delegate their actions!
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mnuClear -> clearAllItems()
                R.id.mnuClearLatest -> clearLatestItem()
                R.id.mnuReset -> resetItems()
                R.id.mnuWebsite -> linkToWebsite()
                R.id.mnuLogOut ->  logout()
            }
            binding.drawerLayout.closeDrawers()
            true
        }
    }

    private fun clearAllItems() {
        listFragment.clearAllItems()
    }

    private fun clearLatestItem() {
        listFragment.clearLatestItem()
    }

    private fun resetItems() {
        listFragment.resetItems()
    }

    private fun logout() {
        loginFragment.logoutFirebase()
        switchTo(loginFragment)
    }

    private fun linkToWebsite() {
        val url = "http://dnd5e.wikidot.com"
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        // if (browse.resolveActivity(requireContext().packageManager) != null) { //dit moet er eigenlijk bij, maar werkt niet
        startActivity(browse)
        //}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // we need to do this to respond correctly to clicks on menu items, otherwise it won't be caught
        if (menuBarToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun switchTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frmListContainer, fragment)
            // if you want to add it to the "back stack" to support the back button, also call this.
            addToBackStack("Fragment_${fragment.id}")
            commit()
        }
    }
}