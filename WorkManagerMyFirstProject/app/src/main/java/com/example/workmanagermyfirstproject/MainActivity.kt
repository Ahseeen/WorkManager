package com.example.workmanagermyfirstproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import androidx.work.PeriodicWorkRequestBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cloudy = Data.Builder().putInt("Cloudy",0).build()

        //Çalışma Koşulları Belirlediğimiz Yapı
       /* val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresBatteryNotLow(false)
            .build()*/



        val myWorkRequest = PeriodicWorkRequestBuilder<RefreshDatabase>(15,TimeUnit.SECONDS)
            .addTag("cloudy")
            .setInputData(cloudy)
            .build()

        WorkManager.getInstance(this).enqueue(myWorkRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this,
                Observer {
                    if(it.state == WorkInfo.State.SUCCEEDED){
                        println("WorkManager başarılı bir şekilde çalıştı :)")
                    }
                    else if(it.state == WorkInfo.State.FAILED){
                        println("Hata Var :( ")
                    }
                }
                )



    }





}