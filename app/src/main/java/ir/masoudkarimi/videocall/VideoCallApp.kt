package ir.masoudkarimi.videocall

import android.app.Application
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import ir.masoudkarimi.videocall.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoCallApp : Application() {

    private var currentName: String? = null
    var client: StreamVideo? = null

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VideoCallApp)
            modules(appModule)
        }
    }

    fun initVideoClient(username: String) {
        if (client == null || username != currentName) {
            StreamVideo.removeClient()
            currentName = username

            client = StreamVideoBuilder(
                context = this,
                apiKey = "kv62zmhadjq4",
                user = User(
                    id = username,
                    name = username,
                    type = UserType.Guest
                )
            ).build()
        }
    }
}