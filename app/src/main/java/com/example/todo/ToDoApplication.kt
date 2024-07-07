package com.example.todo

import android.app.Application
import com.example.todo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToDoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ToDoApplication)
            modules(appModule)
        }
    }
}