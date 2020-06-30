package com.spqrta.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.screens.login.LoginFragmentDirections
import com.spqrta.chatapp.utility.Logger
import com.spqrta.chatapp.utility.SubscriptionManager
import com.spqrta.chatapp.utility.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host)

        toolbar.setTitleTextColor(resources.getColor(R.color.white))
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            AppBarConfiguration(setOf(R.id.chatsFragment))
        )

        navController.addOnDestinationChangedListener { _, destination, bundle ->
            try {
                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host)
                val previousFragment =
                    navHostFragment!!.childFragmentManager.fragments[0] as BaseFragment<*>
                previousFragment.onLeave()
            } catch (e: IndexOutOfBoundsException) {
            }
        }

        if (UserRepository.isLoggedIn()) {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToChatsFragment())
        }

        //todo
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                val token = task.result?.token
                Logger.d(token)
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
