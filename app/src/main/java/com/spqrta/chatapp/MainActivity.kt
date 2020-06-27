package com.spqrta.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.screens.login.LoginFragmentDirections

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(UserRepository.isLoggedIn())

        findNavController(R.id.nav_host).navigate(LoginFragmentDirections.actionLoginFragmentToChatsFragment())
    }
}
