package io.github.szcszshiro.lectref.app.ui.organisms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase

@Composable
fun LectureEdit(
    modifier: Modifier = Modifier,
    initialLectureDTO: ObserveLectureUseCase.LectureDTO?,
    onChangeName: (name: String) -> Unit,

) {

}

@Preview
@Composable
fun LectureEditPreview() {

}