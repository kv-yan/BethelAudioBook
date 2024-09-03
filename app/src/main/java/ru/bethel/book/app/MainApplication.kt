package ru.bethel.book.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.bethel.book.di.appModule
import ru.bethel.book.di.dataModule
import ru.bethel.book.di.domainModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(appModule, dataModule, domainModule)
        }
    }
}