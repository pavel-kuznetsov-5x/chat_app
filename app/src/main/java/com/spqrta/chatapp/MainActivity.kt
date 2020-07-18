package com.spqrta.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.spqrta.chatapp.network.Api
import com.spqrta.chatapp.network.RequestManager
import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.screens.login.LoginFragmentDirections
import com.spqrta.chatapp.utility.Logger
import com.spqrta.chatapp.utility.SubscriptionManager
import com.spqrta.chatapp.utility.Toaster
import com.spqrta.chatapp.utility.base.BaseFragment
import com.spqrta.chatapp.utility.utils.applySchedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.annotations.TestOnly

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
            AppBarConfiguration(setOf(
                R.id.chatsFragment,
                R.id.loginFragment
            ))
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
            updateFcmToken()
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToChatsFragment())
        }
    }

    fun onLogout() {
        navController.navigate(NavGraphDirections.actionGlobalLoginFragment())
    }

    fun updateFcmToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                val token = task.result?.token ?: ""
                Dependencies.api.updateFcmToken(Api.TokenBody(token)).applySchedulers().subscribe({
//                        Logger.d(token)
                }, {
                    Logger.e(it)
                })
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    @TestOnly
    fun getNavigationController(): NavController {
        return navController
    }

}
