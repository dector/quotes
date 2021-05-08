package space.dector.quotes.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainScreen(
                    quote = SampleData.quote(),
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    quote: Quote,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                text = quote.content,
            )
            Text(
                modifier = Modifier
                    .padding(top = 16.dp),
                color = Color.White,
                fontSize = 18.sp,
                text = quote.author,
            )
        }
    }
}

@Composable
@Preview(name = "MainScreen Dark", showBackground = true)
fun MainScreen_Dark_Preview() {
    AppTheme(
        isDark = true,
    ) {
        MainScreen(
            quote = SampleData.quote(),
        )
    }
}

@Composable
fun AppTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDark)
        darkColors()
    else lightColors()

    MaterialTheme(
        colors = colors,
        content = content,
    )
}
