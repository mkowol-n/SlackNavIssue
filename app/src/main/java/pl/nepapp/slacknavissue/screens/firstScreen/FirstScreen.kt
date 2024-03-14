package pl.nepapp.slacknavissue.screens.firstScreen

import androidx.compose.ui.graphics.Color
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize


@Parcelize
object FirstScreen : Screen {
    data class State(
        val bColor: Color,
    ) : CircuitUiState
}