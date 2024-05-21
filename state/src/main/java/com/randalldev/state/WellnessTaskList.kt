package com.randalldev.state

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 *
 * @author Randall.
 * @Date 2024/5/20.
 * @Time 23:15.
 */

@Composable
fun WellnessTaskList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(list, key = { task -> task.id }) {
            WellnessTaskItem(
                taskName = it.label,
                checked = it.checked,
                onCheckedChange = { checked -> onCheckedTask(it, checked) },
                onClose = { onCloseTask(it) })
        }
    }
}
