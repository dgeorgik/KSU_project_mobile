package com.example.ksu_project_mobile.fragments

import android.app.Application
import androidx.room.Room

class App : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "users"
        ).build()
    }
}
