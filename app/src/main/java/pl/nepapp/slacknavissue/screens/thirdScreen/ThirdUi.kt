package pl.nepapp.slacknavissue.screens.thirdScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import pl.nepapp.slacknavissue.screens.secondScreen.SecondScreen
import pl.nepapp.slacknavissue.screens.secondScreen.SecondUi

class ThirdUi : Ui<ThirdScreen.State> {
    @Composable
    override fun Content(state: ThirdScreen.State, modifier: Modifier) {
        FavouriteContent(state)
    }
}

@Composable
private fun FavouriteContent(state: ThirdScreen.State) {
    Box(modifier = Modifier.background(state.bColor).fillMaxSize())
}

class ThirdUiFactory : Ui.Factory {
    override fun create(screen: Screen, context: CircuitContext): Ui<*>? = when (screen) {
        is ThirdScreen -> ThirdUi()
        else -> null
    }
}