package io.github.szcszshiro.lectref.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.szcszshiro.lectref.app.ui.molecules.LectureCard
import io.github.szcszshiro.lectref.app.ui.pages.LectureListPage
import io.github.szcszshiro.lectref.app.ui.theme.LectRefTheme
import io.github.szcszshiro.lectref.presentation.LectureListViewModel

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
                                onClickCard = {},
                                onClickTask = {},
                                onClickAdd = {}
                            )
                        }

                        composable("lecture_detail"){

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