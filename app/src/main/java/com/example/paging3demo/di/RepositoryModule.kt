package com.example.paging3demo.di

import com.example.paging3demo.data.network.MovieAppService
import com.example.paging3demo.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { createRepository(get()) }
}

fun createRepository(
    movieAppService: MovieAppService
): Repository = Repository(movieAppService)