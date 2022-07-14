package io.github.szcszshiro.lectref.app

import android.os.Bundle
import android.util.Log
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
import io.github.szcszshiro.lectref.app.ui.pages.EditLecturePage
import io.github.szcszshiro.lectref.app.ui.pages.LectureDetailPage
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
                                    navController.navigate("edit_lecture")
                                }
                            )
                        }

                        composable(
                            "lecture_detail/{targetId}",
                            arguments = listOf(navArgument("targetId"){
                                type = NavType.IntType
                            })
                        ){ it ->
                            val targetId = it.arguments?.getInt("targetId")?: -1
                            LectureDetailPage(
                                lectureId = targetId,
                                backToList = {
                                    navController.navigate("lecture_list")
                                },
                                editLecture = {
                                    navController.navigate("edit_lecture?lectureId=$targetId")
                                },
                                editTask = { id ->
                                    if (id == null){
                                        recordTaskUseCase.addTask(
                                            targetId,
                                            "Example Task",
                                            "Example task of $targetId",
                                            LocalDateTime.now().plusDays(3)
                                        )
                                    }
                                },
                                editReference = {
                                    if (it == null){
                                        recordReferenceUseCase.addReference(
                                            targetId,
                                            "Example Reference",
                                            "https://example.com",
                                            "Example reference of $targetId"
                                        )
                                    }
                                }
                            )
                        }

                        composable(
                            "edit_lecture?lectureId={lectureId}",
                            arguments = listOf(navArgument("lectureId"){
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            })
                        ){
                            val lectureId = it.arguments?.getString("lectureId")?.toInt()
                            EditLecturePage(
                                lectureId = lectureId,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
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