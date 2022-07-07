package io.github.szcszshiro.lectref.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.github.szcszshiro.lectref.app.ui.theme.LectRefTheme
import io.github.szcszshiro.lectref.usecase.RecordLectureUseCase
import java.time.DayOfWeek
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var recordLectureUseCase: RecordLectureUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LectRefTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Greeting("Android")
                        Text(
                            text = "Push to Add Record",
                            modifier = Modifier.clickable {
                                recordLectureUseCase.addLecture(
                                    "Test Lecture",
                                    "This is Test Lecture Yeaaaahhhhhhh!",
                                    DayOfWeek.MONDAY,
                                    LocalTime.now()
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LectRefTheme {
        Greeting("Android")
    }
}