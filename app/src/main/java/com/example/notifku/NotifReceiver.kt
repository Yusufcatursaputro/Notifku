package com.example.notifku

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.example.notifku.databinding.ActivityMainBinding

class NotifReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val msg = intent?.getStringExtra("TEST")
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        if (msg != null) {
            if (msg == "like"){
                MainActivity.counterLike++
                MainActivity.binding.countLike.text=MainActivity.counterLike.toString()
            }else if (msg == "dislike"){
                MainActivity.counterDislike++
                MainActivity.binding.countDislike.text=MainActivity.counterDislike.toString()
            }


        }
    }
}