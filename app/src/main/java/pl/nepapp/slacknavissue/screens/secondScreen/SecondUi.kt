package pl.nepapp.slacknavissue.screens.secondScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui

class SecondUi : Ui<SecondScreen.State> {
    @Composable
    override fun Content(state: SecondScreen.State, modifier: Modifier) {
        FavouriteContent(state)
    }
}

@Composable
private fun FavouriteContent(state: SecondScreen.State) {
    Box(modifier = Modifier.background(state.bColor).fillMaxSize())
}

class SecondUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is SecondScreen -> SecondUi()
        else -> null
    }
}