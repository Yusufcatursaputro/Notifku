package com.example.notifku

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notifku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        public lateinit var binding: ActivityMainBinding
        public var counterLike = 0
        public var counterDislike = 0
    }

    private val channelId = "TEST_NOTIF"
    private val notifId = 90


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

        binding.btnNotif.setOnClickListener {

            val flag =
                PendingIntent.FLAG_IMMUTABLE

            binding.btnUpdate.setOnClickListener {
                val notifImage = BitmapFactory.decodeResource(resources,
                    R.drawable.hanip_1)



                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.fluent)
                val builder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.fluent)
                    .setLargeIcon(bitmap)
                    .setContentTitle("Notifku")
                    .setContentText("Govannnnnnnnnnn gamtenk")
                    .setStyle(
                        NotificationCompat.BigPictureStyle()
                            .bigPicture(notifImage)
                    )
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .addAction(1, "like", triggerNotif(key = "like"))
                    .addAction(2, "dislike", triggerNotif(key = "dislike"))
                notifManager.notify(notifId, builder.build())

            }

            val intent = Intent(this,  MainActivity::class.java)


            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                flag
            )

            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.fluent)
                .setContentTitle("Notifku")
                .setContentText("Hello World!") // Isi pesan bebas
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(0, "Baca Notif", pendingIntent)

//            val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId, // Id channel
                    "Notifku", // Nama channel notifikasi
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager) {
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            }
            else {
                notifManager.notify(0, builder.build())
            }
        }
    }


    fun triggerNotif(key:String): PendingIntent? {
        val flag =
            PendingIntent.FLAG_IMMUTABLE

        val intent = Intent(this,
            NotifReceiver::class.java).putExtra("TEST", key)

        if (key == "like") {
            val pendingLikeIntent = PendingIntent.getBroadcast(
                this,
                1,
                intent,
                flag
            )
            return pendingLikeIntent
        } else if (key == "dislike") {
            val pendingDislikeIntent = PendingIntent.getBroadcast(
                this,
                2,
                intent,
                flag
            )
            return pendingDislikeIntent
        }
        return null
    }
}