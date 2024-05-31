package ir.masoudkarimi.videocall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.masoudkarimi.videocall.connect.ConnectScreen
import ir.masoudkarimi.videocall.connect.ConnectViewModel
import ir.masoudkarimi.videocall.ui.theme.VideoCallTheme
import ir.masoudkarimi.videocall.video.CallState
import ir.masoudkarimi.videocall.video.VideoCallScreen
import ir.masoudkarimi.videocall.video.VideoCallViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoCallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ConnectRout,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ConnectRout> {
                            val viewModel = koinViewModel<ConnectViewModel>()
                            val state = viewModel.state
                            LaunchedEffect(key1 = state.isConnected) {
                                if (state.isConnected) {
                                    navController.navigate(VideoCallRout) {
                                        popUpTo<ConnectRout> {
                                            inclusive = true
                                        }
                                    }
                                }
                            }

                            ConnectScreen(state = state, onAction = viewModel::onAction)
                        }

                        composable<VideoCallRout> {
                            val viewModel = koinViewModel<VideoCallViewModel>()
                            val state = viewModel.state

                            LaunchedEffect(key1 = state.callState) {
                                if (state.callState == CallState.ENDED) {
                                    navController.navigate(ConnectRout) {
                                        popUpTo(VideoCallRout) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }

                            VideoCallScreen(state = state, onAction = viewModel::onAction)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
data object ConnectRout

@Serializable
data object VideoCallRout