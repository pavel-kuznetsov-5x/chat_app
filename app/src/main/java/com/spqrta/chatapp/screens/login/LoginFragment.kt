package com.spqrta.chatapp.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.utility.base.BaseFragment
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
            UserRepository.login().subscribeManaged {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToChatsFragment())
            }
        }
    }
}
