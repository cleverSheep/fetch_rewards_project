package com.deanmurray.fetchrewardsproject.di

import com.deanmurray.fetchrewardsproject.network.FetchRewardsService
import com.deanmurray.fetchrewardsproject.repository.FetchRewardsRepo
import com.deanmurray.fetchrewardsproject.viewmodel.MainFragmentViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Create the necessary dependencies for the view model...
    single {
        OkHttpClient.Builder()
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(FetchRewardsService::class.java) }
    single { FetchRewardsRepo(get()) }
    // ... finally build the view model
    viewModel { MainFragmentViewModel(get()) }
}

