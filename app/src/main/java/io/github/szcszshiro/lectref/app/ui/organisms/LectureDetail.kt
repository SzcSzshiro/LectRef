package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.szcszshiro.lectref.presentation.LectureDetailViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import io.github.szcszshiro.lectref.usecase.ObserveReferenceUseCase
import io.github.szcszshiro.lectref.usecase.ObserveTaskUseCase
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

@Composable
fun LectureDetail(
    lectureDetailData: LectureDetailViewModel.LectureDetailData,
    modifier: Modifier = Modifier
) {
    
}

@Preview
@Composable
fun LectureDetailPreview() {
    val sampleLectureDTO = ObserveLectureUseCase.LectureDTO(
        1, "Sample Lecture", "This is Sample.", DayOfWeek.MONDAY, LocalTime.now()
    )
    val sampleTaskDTO1 = ObserveTaskUseCase.TaskDTO(
        1, 1, "Sample Task", "This is Sample", LocalDateTime.now().plusDays(1), false
    )
    val sampleTaskDTO2 = sampleTaskDTO1.copy(isDone = true)
    val sampleReferenceDTO = ObserveReferenceUseCase.ReferenceDTO(
        1, 1, "Sample Reference", "https://sample.com", "This is Sample"
    )
    val sampleDetailData = LectureDetailViewModel.LectureDetailData(
        sampleLectureDTO,
        listOf(sampleTaskDTO1, sampleTaskDTO2),
        listOf(sampleReferenceDTO)
    )
    LectureDetail(lectureDetailData = sampleDetailData)
}