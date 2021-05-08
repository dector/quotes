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
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
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
                QuoteScreen(
                    quote = SampleData.quote(),
                )
            }
        }
    }
}

@Composable
fun QuoteScreen(
    quote: Quote,
    isProgressDisplayed: Boolean = false,
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

        if (isProgressDisplayed) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.BottomCenter),
                color = Color.White,
                strokeWidth = 2.5.dp,
            )
        }
    }
}

@Composable
@Preview(name = "QuoteScreen Dark", showBackground = true)
fun QuoteScreen_Dark_Preview() {
    AppTheme(
        isDark = true,
    ) {
        QuoteScreen(
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
