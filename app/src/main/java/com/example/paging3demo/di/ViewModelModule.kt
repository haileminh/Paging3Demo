package com.example.paging3demo.di

import com.example.paging3demo.ui.main.ui.movie.MovieViewModel
import com.example.paging3demo.ui.main.ui.notifications.NotificationsViewModel
import com.example.paging3demo.ui.main.ui.separators.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { NotificationsViewModel(get()) }
}