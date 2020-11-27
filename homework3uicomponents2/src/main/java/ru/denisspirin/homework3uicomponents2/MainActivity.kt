package ru.denisspirin.homework3uicomponents2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMoviesList = FragmentMoviesList().apply {  }

        supportFragmentManager.beginTransaction()
            .add(R.id.main_container, fragmentMoviesList)
            .commit()
    }
}