package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase

@Composable
fun LectureDetail(
    modifier: Modifier = Modifier,
    lectureDTO: ObserveLectureUseCase.LectureDTO,
    taskDTOs: List<ObserveTaskUseCase.TaskDTO>,
    referenceDTOs: List<ObserveReferenceUseCase.ReferenceDTO>,
    onClickEditLecture: () -> Unit,
    onClickDeleteLecture: () -> Unit,
    onClickAddTask: () -> Unit,
    onClickEditTask: (taskId: Int) -> Unit,
    onClickDeleteTask: (taskId: Int) -> Unit,
    onClickAddReference: () -> Unit,
    onClickEditReference: (referenceId: Int) -> Unit,
    onClickDeleteReference: (referenceId: Int) -> Unit
) {

}