package com.example.servicehw

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myService: MyService
    var mBound = false
    lateinit var mConnection: ServiceConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener() {
            if (mBound) {
//                var num: Int = myService.gerRandomNumber()
//                Log.i("myLogs", "$num")

                if (edValue.text.isEmpty()) {

                } else {
                    tvResult.text = (myService.factorial(edValue.text.toString().toInt())).toString()
                }

            }
        }

        mConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.i("myLogs", "onServiceConnected")
                myService = MyService.MyBinder().getService()
                mBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i("myLogs", "onServiceDisconnected")
                mBound = false
            }
        }

    }

    override fun onStart() {
        super.onStart()
        var intent: Intent = Intent(this, MyService::class.java)
        bindService(intent, mConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mBound) {
            unbindService(mConnection)
            mBound = false
        }
    }

}