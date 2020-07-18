package com.spqrta.chatapp

import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.spqrta.chatapp.entity.Chat
import com.spqrta.chatapp.network.MockApi
import com.spqrta.chatapp.screens.chat.ChatFragment
import com.spqrta.chatapp.screens.chat.MessagesAdapter
import kotlinx.android.synthetic.main.fragment_chat.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class PmLocalTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    @Test
    fun test() {

        Dependencies.api = MockApi

        activityRule.activity.let {
            it.runOnUiThread {
                it.getNavigationController().navigate(R.id.chatFragment, Bundle().apply {
//                    putParcelable("chat", Chat(
//                    0,
//                    "Test chat",
//                    Chat.AVATAR,
//                    1000
//                    ))
                    putParcelable("chat", Chat.test())
                })
            }
        }
        sleep(1000)
//        onView(withId(R.id.rvMessages)).perform(swipeDown())
//        onView(withId(R.id.rvMessages)).perform(swipeDown())
//        onView(withId(R.id.rvMessages)).perform(swipeDown())
//        onView(withId(R.id.rvMessages)).perform(swipeDown())
//        onView(withId(R.id.rvMessages)).perform(swipeDown())
        sleep(60000)
    }
}
