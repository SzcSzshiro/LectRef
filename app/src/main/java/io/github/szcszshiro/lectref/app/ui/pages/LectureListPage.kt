package io.github.szcszshiro.lectref.app.ui.pages

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.szcszshiro.lectref.app.ui.organisms.LectureList
import io.github.szcszshiro.lectref.app.ui.templates.LectureListTemplate
import io.github.szcszshiro.lectref.presentation.LectureListViewModel

@Composable
fun LectureListPage(
    onClickCard: (lectureId: Int) -> Unit,
    onClickTask: (lectureId: Int) -> Unit,
    onClickAdd: () -> Unit
){
    val vm: LectureListViewModel = hiltViewModel()
    val cardDataList = vm.lectureCardsLiveData.observeAsState()
    LectureListTemplate {
        LectureList(
            lectureCardDataList = cardDataList.value?: emptyList(),
            onClickCard,
            onClickTask,
            onClickAdd
        )
    }
}