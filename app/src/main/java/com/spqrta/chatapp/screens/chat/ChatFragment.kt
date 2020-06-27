package com.spqrta.chatapp.screens.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.repository.MessagesRepository
import com.spqrta.chatapp.utility.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseFragment<MainActivity>() {

    val args: ChatFragmentArgs by navArgs()
    private val adapter = MessagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMessages.layoutManager = LinearLayoutManager(context)
        rvMessages.adapter = adapter

        MessagesRepository.getMessages(args.chatId).subscribeManaged {
            adapter.updateItems(it)
        }
    }
}
