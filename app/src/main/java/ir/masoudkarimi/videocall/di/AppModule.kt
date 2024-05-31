package ir.masoudkarimi.videocall.di

import ir.masoudkarimi.videocall.VideoCallApp
import ir.masoudkarimi.videocall.connect.ConnectViewModel
import ir.masoudkarimi.videocall.video.VideoCallViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory {
        val app = androidContext().applicationContext as VideoCallApp
        app.client
    }

    viewModel {
        ConnectViewModel(get())
    }

    viewModel {
        VideoCallViewModel(get())
    }
}