package com.randalldev.learncompose

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randalldev.layouts.BasicLayoutsActivity
import com.randalldev.learncompose.ui.theme.LearnComposeTheme
import com.randalldev.state.StateActivity
import com.randalldev.sunflower.GardenActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    var expanded by remember { mutableStateOf(false) }

    val expandPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = expandPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello,")
                Text(text = "$name!", style = MaterialTheme.typography.headlineLarge)
            }
            ElevatedButton(onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "more" else "less")
            }
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Basics Codelab!")
        Button(modifier = Modifier.padding(24.dp), onClick = onContinueClicked) {
            Text(text = "Continue!")
        }
        Button(modifier = Modifier.padding(24.dp), onClick = {
            val intent = Intent(context, BasicLayoutsActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Go 2 BasicLayout!")
        }
        Button(modifier = Modifier.padding(24.dp), onClick = {
            val intent = Intent(context, StateActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Go 2 BasicState!")
        }
        Button(modifier = Modifier.padding(24.dp), onClick = {
            val intent = Intent(context, GardenActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Go 2 Migration!")
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnComposeTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {

        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else
            Greetings(modifier)
    }
}

@Composable
private fun Greetings(modifier: Modifier, names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = modifier.padding(4.dp)) {
        items(items = names) {
            Greeting(name = it)
        }
    }
}
