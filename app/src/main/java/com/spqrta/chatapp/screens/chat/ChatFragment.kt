package com.spqrta.chatapp.screens.chat

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.repository.MessagesRepository
import com.spqrta.chatapp.utility.base.BaseFragment
import com.spqrta.chatapp.utility.utils.textString
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

        MessagesRepository.getMessages(args.chat).subscribeManaged {
            adapter.updateItems(it)
        }

        bSend.setOnClickListener {
            sendMessage()
        }

        etMessage.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_ENTER -> {
                        sendMessage()
                        return@setOnKeyListener true
                    }
                }
            }
            return@setOnKeyListener false
        }

    }

    fun sendMessage() {
        MessagesRepository.sendMessage(args.chat, etMessage.textString()).subscribeManaged {
            adapter.addItemsAndUpdate(listOf(it))
            rvMessages.scrollToPosition(adapter.itemCount-1)
        }
    }
}
