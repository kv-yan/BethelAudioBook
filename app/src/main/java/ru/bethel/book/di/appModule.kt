package ru.bethel.book.di

import android.content.Context
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bethel.book.view_model.MainViewModel

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(get<Context>())
    }
}