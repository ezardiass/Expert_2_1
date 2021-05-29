package com.dicoding.picodiploma.architecturecomponentsub1.di

import com.dicoding.picodiploma.core.domain.usecase.Interactor
import com.dicoding.picodiploma.core.domain.usecase.MovieUseCase
import com.dicoding.picodiploma.architecturecomponentsub1.viewmodel.DetailMoviesViewModel
import com.dicoding.picodiploma.architecturecomponentsub1.viewmodel.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module {
        factory<MovieUseCase> { Interactor(get()) }
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { DetailMoviesViewModel(get()) }
    }
}