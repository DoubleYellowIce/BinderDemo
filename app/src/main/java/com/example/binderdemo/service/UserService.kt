package com.example.binderdemo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.binderdemo.aidl.UserManager
import com.example.binderdemo.model.User

class UserService : Service() {

    val users = mutableListOf<User>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return stub
    }

    private val stub: UserManager.Stub = object : UserManager.Stub() {
        override fun addUser(user: User?) {
            Log.d("UserService", "addUser: $user")
            users.add(user!!)
            var i = 1
            for (u in users) {
                Log.d("UserService", "${i++}st user" + ": $u")
            }
        }

        override fun getUser(index: Int): User {
            //获取用户
            val user = users[index]
            Log.d("UserService", "getUser: $user")
            return user
        }
    }

}