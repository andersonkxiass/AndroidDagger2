package com.example.tools

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tools.di.components.LoginComponent

class MainActivity : AppCompatActivity() {

    // Reference to the Login graph
    lateinit var loginComponent: LoginComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creation of the login graph using the application graph
        loginComponent = (applicationContext as MyApplication).appComponent.loginComponent().create()
        // Make Dagger instantiate @Inject fields in LoginActivity
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
