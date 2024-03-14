package pl.nepapp.slacknavissue.screens.secondScreen

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
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdPresenter
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdScreen
import kotlin.random.Random

class SecondPresenter(
) : Presenter<SecondScreen.State> {

    @Composable
    override fun present(): SecondScreen.State {
        val color1 = rememberRetained() {
            Random.nextInt(256)
        }
        val color2 = rememberRetained {
            Random.nextInt(256)
        }
        val color3 = rememberRetained {
            Random.nextInt(256)
        }
        val bColor by remember {
            mutableStateOf(
                Color(
                    color1,
                    color2,
                    color3
                )
            )
        }

        return SecondScreen.State(
            bColor = bColor,
        )
    }
}


class SecondPresenterFactory : Presenter.Factory {
    override fun create(
        screen: Screen,
        navigator: Navigator,
        context: CircuitContext,
    ): Presenter<*>? = when (screen) {
        is SecondScreen -> SecondPresenter()
        else -> null
    }
}