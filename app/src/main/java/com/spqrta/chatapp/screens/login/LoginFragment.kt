package com.spqrta.chatapp.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.utility.Logger
import com.spqrta.chatapp.utility.Toaster
import com.spqrta.chatapp.utility.base.BaseFragment
import com.spqrta.chatapp.utility.utils.textString
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<MainActivity>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bLogin.setOnClickListener {
            UserRepository.login(etLogin.textString(), etPassword.textString()).subscribeManaged ({
                mainActivity().updateFcmToken()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToChatsFragment())
            }, {
                Toaster.show(it)
                Logger.e(it)
            })
        }

        bSpqrta.setOnClickListener {
            etLogin.setText("spqrta")
            etPassword.setText("007007")
        }

        bCucumber.setOnClickListener {
            etLogin.setText("cucumber007")
            etPassword.setText("007007")
        }
    }
}
