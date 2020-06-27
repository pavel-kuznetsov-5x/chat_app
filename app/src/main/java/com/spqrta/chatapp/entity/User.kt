package com.spqrta.chatapp.entity

import android.os.Parcelable
import com.spqrta.chatapp.repository.UserRepository
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val id: Int,
    val displayName: String,
    val avatarUrl: String
): Parcelable {
    companion object {
        fun test() = if(Math.random() > 0.5) {
            UserRepository.USER_1
        } else {
            UserRepository.USER_2
        }
    }
}