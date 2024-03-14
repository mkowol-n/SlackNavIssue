package pl.nepapp.slacknavissue.screens.secondScreen

import androidx.compose.ui.graphics.Color
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize


@Parcelize
object SecondScreen : Screen {
    data class State(
        val bColor: Color,
    ) : CircuitUiState
}