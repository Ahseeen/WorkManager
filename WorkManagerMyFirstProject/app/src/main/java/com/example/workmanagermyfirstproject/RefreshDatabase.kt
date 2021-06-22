package com.example.workmanagermyfirstproject
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters


class RefreshDatabase(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        refreshDatabaseCloudy()
        return Result.success()
    }
    fun refreshDatabaseCloudy(){
        val sharedPreferences = applicationContext.getSharedPreferences("package com.example.workmanagermyfirstproject",
            Context.MODE_PRIVATE)
        var savedCloudy = sharedPreferences.getInt("Cloudy",0)
        savedCloudy = savedCloudy + 2
        println("Cloudly: "+savedCloudy)
        sharedPreferences.edit().putInt("Cloudy",savedCloudy).apply()
    }


}