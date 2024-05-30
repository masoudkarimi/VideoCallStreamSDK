package ir.masoudkarimi.videocall.video

sealed interface VideoCallAction {
    data object OnDisconnect: VideoCallAction
    data object JoinCall: VideoCallAction
}