package com.example.paging3demo.di

import com.example.paging3demo.ui.main.ui.home.HomeViewModel
import com.example.paging3demo.ui.main.ui.separators.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
}