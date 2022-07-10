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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.szcszshiro.lectref.app.ui.molecules.LectureCard
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "lecture_list"
                    ){
                        composable("lecture_list"){
                            val vm: LectureListViewModel by viewModels()
                            val cardDataList = vm.lectureCardsLiveData.observeAsState()
                            Scaffold(
                                topBar = {
                                    TopAppBar(
                                        title = { Text(text = "LectRef")}
                                    )
                                },
                                backgroundColor = MaterialTheme.colors.background
                            ) {
                                LazyColumn {
                                    items(cardDataList.value?: emptyList()){ cardData ->
                                        LectureCard(
                                            lectureName = cardData.lectureName,
                                            taskNum = cardData.taskNum,
                                            weekValue = cardData.weekValue,
                                            startTimeValue = cardData.startTimeValue,
                                            onClickCard = {},
                                            onClickTask = {},
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                            }
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