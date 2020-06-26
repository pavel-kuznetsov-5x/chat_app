package com.spqrta.chatapp.screens.chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.repository.ChatsRepository
import com.spqrta.reusables.base.BaseFragment
import com.spqrta.reusables.recycler.ArrayRecyclerAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvChats.layoutManager = LinearLayoutManager(context)
        rvChats.adapter = adapter

        ChatsRepository.getChats().subscribeManaged {
            adapter.updateItems(it)
        }
    }
}
