package pl.nepapp.slacknavissue.screens.firstScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui

class FirstUi : Ui<FirstScreen.State> {
    @Composable
    override fun Content(state: FirstScreen.State, modifier: Modifier) {
        FavouriteContent(state)
    }
}

@Composable
private fun FavouriteContent(state: FirstScreen.State) {
    Box(modifier = Modifier
        .background(state.bColor)
        .fillMaxSize())
}

class FirstUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is FirstScreen -> FirstUi()
        else -> null
    }
}