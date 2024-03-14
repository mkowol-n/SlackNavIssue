package pl.nepapp.slacknavissue.screens.firstScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import pl.nepapp.slacknavissue.screens.secondScreen.SecondScreen
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdPresenter
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdScreen
import kotlin.random.Random

class FirstPresenter(
) : Presenter<FirstScreen.State> {

    @Composable
    override fun present(): FirstScreen.State {
        val color1 = rememberRetained() {
            Random.nextInt(256)
        }
        val color2 = rememberRetained {
            Random.nextInt(256)
        }
        val color3 = rememberRetained {
            Random.nextInt(256)
        }
        val color by remember {
            mutableStateOf(
                Color(
                    color1,
                    color2,
                    color3
                )
            )
        }

        return FirstScreen.State(
            bColor = color,
        )
    }
}


class FirstPresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext,
    ): Presenter<*>? = when (screen) {
        is FirstScreen -> FirstPresenter()
        else -> null
    }
}