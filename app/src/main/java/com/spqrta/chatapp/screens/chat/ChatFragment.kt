package com.spqrta.chatapp.screens.chat

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.spqrta.chatapp.EndlessScrollListener
import com.spqrta.chatapp.MainActivity

import com.spqrta.chatapp.R
import com.spqrta.chatapp.repository.MessagesRepository
import com.spqrta.chatapp.utility.Toaster
import com.spqrta.chatapp.utility.base.BaseFragment
import com.spqrta.chatapp.utility.utils.textString
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : BaseFragment<MainActivity>() {

    val args: ChatFragmentArgs by navArgs()
    lateinit var adapter: MessagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity().supportActionBar?.title = args.chat.displayName

        adapter = MessagesAdapter(args.chat, logView)
        rvMessages.layoutManager = LinearLayoutManager(context)
        rvMessages.adapter = adapter
        rvMessages.addOnScrollListener(adapter.scrollListener)

        logView.setOnClickListener {
//            adapter.loadMore()
        }

        init()

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


    private fun init() {
        MessagesRepository.getMessages(args.chat, MessagesAdapter.FETCH).subscribeManaged {
            adapter.updateItems(it)
            rvMessages.scrollToPosition(adapter.itemCount - 1)
        }
    }

    //todo handle case if user exits before message has sent
    private fun sendMessage() {
        val message = etMessage.textString()
        if (message.isNotBlank()) {
            MessagesRepository.sendMessage(args.chat, message).subscribeManaged({
                adapter.addItemsAndUpdate(listOf(it))
                rvMessages.scrollToPosition(adapter.itemCount - 1)
            }, {
                Toaster.show(it)
            })
        }
    }
}
