package com.example.weatherappassessment.core.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object LifeCycleUtil {

    fun <T> LifecycleOwner.collectInLifecycleScope(
        flow: Flow<T>,
        lifecycleState : Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (T) -> Unit
    ) {
        this.lifecycleScope.launch {
            this@collectInLifecycleScope.lifecycle.repeatOnLifecycle(lifecycleState) {
//            repeatOnLifecycle(lifecycleState) {
                flow.collectLatest {
                    action.invoke(it)
                }
            }
        }
    }
}