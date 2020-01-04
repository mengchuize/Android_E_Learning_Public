package com.e_learning_kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Myreceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        Toast.makeText(
            context,
            "服务器已更新" + " 更新内容是是:" + intent.getAction() + " key值是:" + intent.getExtras()?.getString("key"),
            Toast.LENGTH_SHORT
        ).show()
    }
}