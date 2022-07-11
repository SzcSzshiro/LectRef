package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.szcszshiro.lectref.app.ui.molecules.LectureCard
import io.github.szcszshiro.lectref.presentation.LectureListViewModel

@Composable
fun LectureList(
    lectureCardDataList: List<LectureListViewModel.LectureCardData>,
    onClickCard: (lectureId: Int) -> Unit,
    onClickTask: (lectureId: Int) -> Unit,
    onClickAdd: () -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        items(lectureCardDataList){ cardData ->
            LectureCard(
                lectureName = cardData.lectureName,
                taskNum = cardData.taskNum,
                weekValue = cardData.weekValue,
                startTimeValue = cardData.startTimeValue,
                onClickCard = { onClickCard(cardData.id) },
                onClickTask = { onClickTask(cardData.id) },
                modifier = Modifier.padding(8.dp)
            )
        }
        item {
            FloatingActionButton(
                onClick = { onClickAdd() },
                elevation = FloatingActionButtonDefaults.elevation(),
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Push to add lecture."
                )
            }
        }
    }
}