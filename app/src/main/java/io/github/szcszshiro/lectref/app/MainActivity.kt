package io.github.szcszshiro.lectref.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.github.szcszshiro.lectref.app.ui.pages.*
import io.github.szcszshiro.lectref.app.ui.theme.LectRefTheme
import io.github.szcszshiro.lectref.usecase.RecordLectureUseCase
import io.github.szcszshiro.lectref.usecase.RecordReferenceUseCase
import io.github.szcszshiro.lectref.usecase.RecordTaskUseCase
import java.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                                onClickTask = {
                                    navController.navigate("lecture_detail/$it")
                                },
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
                        ){
                            val targetId = it.arguments?.getInt("targetId")?: -1
                            LectureDetailPage(
                                lectureId = targetId,
                                backToList = {
                                    navController.popBackStack()
                                },
                                editLecture = {
                                    navController.navigate("edit_lecture?lectureId=$targetId")
                                },
                                editTask = { id ->
                                    navController.navigate("edit_task/$targetId?taskId=$id")
                                },
                                editReference = { id ->
                                    navController.navigate("edit_reference/$targetId?referenceId=$id")
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

                        composable(
                            "edit_task/{lectureId}?taskId={taskId}",
                            arguments = listOf(
                                navArgument("lectureId"){
                                    type = NavType.IntType
                                },
                                navArgument("taskId"){
                                    type = NavType.StringType
                                    nullable= true
                                    defaultValue = null
                                }
                            )
                        ){
                            val lectureId = it.arguments?.getInt("lectureId")?: -1
                            val taskId = it.arguments?.getString("taskId")?.toIntOrNull()
                            EditTaskPage(
                                taskId = taskId,
                                lectureId = lectureId,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable(
                            "edit_reference/{lectureId}?referenceId={referenceId}",
                            arguments = listOf(
                                navArgument("lectureId"){
                                    type = NavType.IntType
                                },
                                navArgument("referenceId"){
                                    type = NavType.StringType
                                    nullable= true
                                    defaultValue = null
                                }
                            )
                        ){
                            val lectureId = it.arguments?.getInt("lectureId")?: -1
                            val referenceId = it.arguments?.getString("referenceId")?.toIntOrNull()
                            EditReferencePage(
                                referenceId = referenceId,
                                lectureId = lectureId,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}