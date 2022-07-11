package io.github.szcszshiro.lectref.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import io.github.szcszshiro.lectref.app.ui.organisms.LectureDetail
import io.github.szcszshiro.lectref.app.ui.pages.LectureListPage
import io.github.szcszshiro.lectref.app.ui.theme.LectRefTheme
import io.github.szcszshiro.lectref.presentation.LectureDetailViewModel
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
    @Inject lateinit var recordReferenceUseCase: RecordReferenceUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LectRefTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "lecture_list"
                    ){
                        composable("lecture_list"){
                            LectureListPage(
                                onClickCard = {
                                    navController.navigate("lecture_detail/$it")
                                },
                                onClickTask = {},
                                onClickAdd = {
                                    recordLectureUseCase.addLecture(
                                        "Example Lecture",
                                        "This is example lecture.\nFor Test.",
                                        DayOfWeek.MONDAY,
                                        LocalTime.now()
                                    )
                                }
                            )
                        }

                        composable(
                            "lecture_detail/{targetId}",
                            arguments = listOf(navArgument("targetId"){type = NavType.IntType})
                        ){
                            val targetId = navController.currentBackStackEntry?.arguments?.getInt("targetId")?: -1
                            val vm: LectureDetailViewModel = hiltViewModel()
                            val lecture = vm.getLectureDetailLiveData(targetId).observeAsState()
                            val tasks = vm.getTasksLiveData(targetId).observeAsState()
                            val references = vm.getReferencesLiveData(targetId).observeAsState()

                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = { Text("LectRef") }
                                    )
                                }
                            ) {
                                LectureDetail(
                                    lectureDTO = lecture.value,
                                    taskDTOs = tasks.value?: emptyList(),
                                    referenceDTOs = references.value?: emptyList(),
                                    onClickEditLecture = {},
                                    onClickDeleteLecture = {},
                                    onClickAddTask = {
                                        recordTaskUseCase.addTask(
                                            targetId,
                                            "Test Task",
                                            "Test Task of $targetId Lecture",
                                            LocalDateTime.now().plusDays(1)
                                        )
                                    },
                                    onClickFinishTask = {},
                                    onClickEditTask = {},
                                    onClickDeleteTask = {},
                                    onClickAddReference = {
                                        recordReferenceUseCase.addReference(
                                            targetId,
                                            "Test Reference",
                                            "https://example.com",
                                            "Test Reference of $targetId Lecture."
                                        )
                                    },
                                    onClickOpenReference = {},
                                    onClickEditReference = {},
                                    onClickDeleteReference = {}
                                )
                            }
                        }

                        composable("edit_lecture"){

                        }

                        composable("edit_task"){

                        }

                        composable("edit_reference"){

                        }
                    }
                }
            }
        }
    }
}