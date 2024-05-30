package ir.masoudkarimi.videocall.connect

data class ConnectState(
    val name: String = "",
    val isConnected: Boolean = false,
    val errorMessage: String? = null,
)
