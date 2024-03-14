package pl.nepapp.slacknavissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import pl.nepapp.slacknavissue.screens.firstScreen.FirstPresenter
import pl.nepapp.slacknavissue.screens.firstScreen.FirstPresenterFactory
import pl.nepapp.slacknavissue.screens.firstScreen.FirstScreen
import pl.nepapp.slacknavissue.screens.firstScreen.FirstUiFactory
import pl.nepapp.slacknavissue.screens.secondScreen.SecondPresenterFactory
import pl.nepapp.slacknavissue.screens.secondScreen.SecondScreen
import pl.nepapp.slacknavissue.screens.secondScreen.SecondUiFactory
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdPresenterFactory
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdScreen
import pl.nepapp.slacknavissue.screens.thirdScreen.ThirdUiFactory
import pl.nepapp.slacknavissue.ui.theme.SlackNavIssueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val circuit = Circuit.Builder()
            .addPresenterFactories(
                listOf(
                    FirstPresenterFactory(),
                    SecondPresenterFactory(),
                    ThirdPresenterFactory()
                )
            )
            .addUiFactories(
                listOf(
                    FirstUiFactory(),
                    SecondUiFactory(),
                    ThirdUiFactory()
                )
            )
            .build()
        setContent {
            SlackNavIssueTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val backStack =
                        rememberSaveableBackStack(
                            FirstScreen
                        )
                    val navigator = rememberCircuitNavigator(backStack = backStack) {

                    }

                    Scaffold(
                        bottomBar = {
                            BottomNavigation(
                                currentScreen = when (navigator.peek()) {
                                    is FirstScreen -> CurrentScreen.First
                                    is SecondScreen -> CurrentScreen.Second
                                    is ThirdScreen -> CurrentScreen.Third
                                    else -> throw Exception()
                                },
                                onClick = {
                                    val toScreen = when (it) {
                                        CurrentScreen.First -> FirstScreen
                                        CurrentScreen.Second -> SecondScreen
                                        CurrentScreen.Third -> ThirdScreen
                                    }

                                    navigator.resetRoot(
                                        newRoot = toScreen,
                                        saveState = true,
                                        restoreState = true
                                    )
                                }
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {

                            NavigableCircuitContent(
                                navigator = navigator,
                                backStack = backStack,
                                circuit = circuit
                            )
                        }
                    }
                }
            }
        }
    }
}


enum class CurrentScreen {
    First, Second, Third
}

@Composable
fun BottomNavigation(currentScreen: CurrentScreen, onClick: (CurrentScreen) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        BottomBarItem(
            modifier = Modifier
                .clickable { onClick(CurrentScreen.First) }
                .weight(1f),
            isSelected = currentScreen == CurrentScreen.First,
            text = "First"
        )
        BottomBarItem(
            modifier = Modifier
                .clickable { onClick(CurrentScreen.Second) }
                .weight(1f),
            isSelected = currentScreen == CurrentScreen.Second,
            text = "Second"
        )
        BottomBarItem(
            modifier = Modifier
                .clickable { onClick(CurrentScreen.Third) }
                .weight(1f),
            isSelected = currentScreen == CurrentScreen.Third,
            text = "Third"
        )
    }
}

@Composable
fun BottomBarItem(modifier: Modifier, isSelected: Boolean, text: String) {
    val color by animateColorAsState(
        targetValue = if (isSelected) Color.Black else Color.Gray,
        label = ""
    )
    Box(modifier = modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            color = color
        )
    }
}