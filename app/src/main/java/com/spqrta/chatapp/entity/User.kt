package com.spqrta.chatapp.entity

import android.os.Parcelable
import com.spqrta.chatapp.repository.UserRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val username: String,
    val avatarUrl: String
): Parcelable {

    val displayName: String
        get() = username

    companion object {
        fun test() = if(Math.random() > 0.5) {
            UserRepository.USER_1
        } else {
            UserRepository.USER_2
        }
    }
}