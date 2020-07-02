package com.spqrta.chatapp.screens.chats

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.repository.ChatsRepository
import com.spqrta.chatapp.repository.UserRepository
import com.spqrta.chatapp.utility.Toaster
import com.spqrta.chatapp.utility.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chats.*

//import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : BaseFragment<MainActivity>() {

    private val adapter = ChatsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    @SuppressLint("DefaultLocale")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity().supportActionBar?.title = "${UserRepository.currentUser?.displayName?.capitalize()} chats"

        rvChats.layoutManager = LinearLayoutManager(context)
        rvChats.adapter = adapter

        adapter.onItemClickListener = {
            findNavController().navigate(ChatsFragmentDirections.actionChatsFragmentToChatFragment(it))
        }

        update()

        swipeToRefresh.setOnRefreshListener {
            update()
        }

        bLogout.setOnClickListener {
            UserRepository.logout()
            mainActivity().onLogout()
        }
    }

    private fun update() {
        ChatsRepository.getChats().doOnEvent { t1, t2 ->
            swipeToRefresh.isRefreshing = false
        }.subscribeManaged ({
            adapter.updateItems(it)
        }, {
            Toaster.show(it)
        })
    }
}
