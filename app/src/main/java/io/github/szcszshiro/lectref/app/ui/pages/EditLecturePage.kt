package io.github.szcszshiro.lectref.app.ui.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.szcszshiro.lectref.app.ui.organisms.LectureEdit
import io.github.szcszshiro.lectref.app.ui.templates.EditTemplate
import io.github.szcszshiro.lectref.presentation.EditLectureViewModel
import io.github.szcszshiro.lectref.usecase.ObserveLectureUseCase
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.LocalTime

@Composable
fun EditLecturePage(
    lectureId: Int?,
    onBack: () -> Unit
) {
    val vm: EditLectureViewModel = hiltViewModel()
    val context = LocalContext.current
    val target: ObserveLectureUseCase.LectureDTO?
    runBlocking {
        target = vm.getLectureDTO(lectureId?: -1)
    }

    var name by remember {
        mutableStateOf(target?.name?: "")
    }
    var description by remember {
        mutableStateOf(target?.description?: "")
    }
    var week by remember {
        mutableStateOf(target?.week?: DayOfWeek.MONDAY)
    }
    var startTime by remember {
        mutableStateOf(target?.startTime?: LocalTime.now())
    }

    EditTemplate {
        LectureEdit(
            name = name,
            description = description,
            week = week,
            startTime = startTime,
            onChangeName = { name = it },
            onChangeDescription = { description = it },
            onChangeWeek = { week = it },
            onChangeStartTime = { startTime = it },
            onPushCancel = onBack,
            onPushOk = {
                if (!vm.checkIsNameOk(name)) {
                    Toast.makeText(context, "Invalid Name", Toast.LENGTH_SHORT).show()
                    return@LectureEdit
                }
                if (!vm.checkIsDescriptionOk(description)) {
                    Toast.makeText(context, "Invalid Description", Toast.LENGTH_SHORT).show()
                    return@LectureEdit
                }
                onBack()
                if (lectureId == null || target == null){
                    vm.addLecture(name, description, week, startTime)
                }else{
                    vm.editLecture(lectureId, name, description, week, startTime)
                }
            }
        )
    }
}