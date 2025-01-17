package ir.masoudkarimi.videocall.connect

sealed interface ConnectAction {
    data class OnNameChanged(val name: String): ConnectAction
    data object OnConnectClick: ConnectAction
}