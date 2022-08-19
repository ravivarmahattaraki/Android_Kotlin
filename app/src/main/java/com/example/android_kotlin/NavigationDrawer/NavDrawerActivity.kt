package com.example.android_kotlin.NavigationDrawer

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.android_kotlin.Activities.FirstActivity
import com.example.android_kotlin.DesignPattern.CreationalPatterns.CreationalPatternActivity
import com.example.android_kotlin.Fragments.FragmentActivity
import com.example.android_kotlin.ListView.ListViewActivity
import com.example.android_kotlin.PdfReader.PdfReaderActivity
import com.example.android_kotlin.R
import com.example.android_kotlin.RecyclerView.RecyclerViewActivity

class NavDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var toolBar : Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_nav_drawer)
        toolBar = findViewById(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        setSupportActionBar(toolBar)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout,
            R.string.nav_open, R.string.nav_close,
        )
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        drawerLayout.open()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_item_about ->{
                Toast.makeText(this,"About",Toast.LENGTH_LONG).show()
            }
            R.id.nav_item_help ->{

            }
            R.id.nav_item_activites ->{
                startMyActivity(FirstActivity::class.java)
            }
            R.id.nav_item_listview ->{
                startMyActivity(ListViewActivity::class.java)
            }
            R.id.nav_item_recycler_view ->{
                startMyActivity(RecyclerViewActivity::class.java)
            }
            R.id.nav_item_fragments ->{
                startMyActivity(FragmentActivity::class.java)
            }
            R.id.nav_item_pdf_viewer ->{
                startMyActivity(PdfReaderActivity::class.java)
            }
            R.id.nav_item_creational_patterns ->{
                startMyActivity(CreationalPatternActivity::class.java)
            }
            R.id.nav_item_structural_patterns ->{
                startMyActivity(FragmentActivity::class.java)
            }
            R.id.nav_item_behavioural_patterns ->{
                startMyActivity(PdfReaderActivity::class.java)
            }
        }
        //return true item click state will be saved
        return false
    }

    private fun startMyActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}