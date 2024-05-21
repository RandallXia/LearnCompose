/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.randalldev.state

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {

    private val _tasks = getWellnessTasks().toMutableStateList()

    val task: List<WellnessTask>
        get() = _tasks

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) {
        _tasks.find { it.id == item.id }?.let { task -> task.checked = checked }
    }

    fun remove(taskItem: WellnessTask) {
        _tasks.remove(taskItem)
    }

    private fun getWellnessTasks() = List(50) { i -> WellnessTask(id = i, label = "Task # $i") }
}
