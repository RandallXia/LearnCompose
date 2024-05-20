package com.randalldev.state

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 *
 * @author Randall.
 * @Date 2024/5/20.
 * @Time 23:15.
 */

@Composable
fun WellnessTaskList(modifier: Modifier = Modifier, list: List<WellnessTask> = remember { getWellnessTasks() }) {
    LazyColumn(modifier = modifier) {
        items(list) {
            WellnessTaskItem(taskName = it.label)
        }
    }
}

private fun getWellnessTasks() = List(50) { i -> WellnessTask(id = i, label = "Task # $i") }