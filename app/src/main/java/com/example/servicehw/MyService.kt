package com.example.servicehw

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.util.*

class MyService : Service() {

    var mBinder: IBinder = MyBinder()

    var mGeneraotr = Random()

    override fun onBind(intent: Intent?): IBinder? {
        Log.i("myLogs", "onBind Service")
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("myLogs", "onCreate Service")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("myLogs", "onDestroy Service")
    }

    fun gerRandomNumber(): Int {
        return mGeneraotr.nextInt(100)
    }

    fun factorial(value: Int): Int {
        var result: Int = 1
        for (i in 1..value){
             result*=i
        }
        return result
    }

    class MyBinder : Binder() {
        fun getService(): MyService {
            return MyService()
        }
    }

}




