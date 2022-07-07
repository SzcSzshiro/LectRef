package io.github.szcszshiro.lectref.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.szcszshiro.lectref.app.ui.theme.LectRefTheme
import io.github.szcszshiro.lectref.presentation.LectureListViewModel
import io.github.szcszshiro.lectref.usecase.RecordLectureUseCase
import io.github.szcszshiro.lectref.usecase.RecordReferenceUseCase
import io.github.szcszshiro.lectref.usecase.RecordTaskUseCase
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var recordLectureUseCase: RecordLectureUseCase
    @Inject lateinit var recordTaskUseCase: RecordTaskUseCase
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: LectureListViewModel by viewModels()
            LectRefTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Button(onClick = {
                            recordLectureUseCase.addLecture(
                                "Test Lecture",
                                "description",
                                DayOfWeek.MONDAY,
                                LocalTime.now().plusHours(15)
                            )
                        }) {
                            Text(text = "Click to Add")
                        }
                        
                        val lectureList = vm.getLectureLivedata().observeAsState()
                        val taskList = vm.getTasksLivedata().observeAsState()
                        lectureList.value?.forEach { lecture ->
                            Text(
                                text = "${lecture.id} ${lecture.name}",
                                modifier = Modifier.clickable {
                                    recordTaskUseCase.addTask(
                                        lecture.id!!,
                                        "${lecture.name}'s Test Task",
                                        "description",
                                        LocalDateTime.now().plusDays(10)
                                    )
                                }
                            )
                            val tasks = taskList.value?.filter { it.lectureId == lecture.id }
                            tasks?.forEach { task ->
                                Text(
                                    text = "${task.id} ${task.name}",
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .clickable {
                                            recordTaskUseCase.removeTask(task.id!!)
                                        }
                                )
                            }
                        }
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