package com.talentsarena.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.talentsarena.core.vm.BaseViewModel
import com.talentsarena.core.udf.UiActionEvent
import com.talentsarena.core.udf.UiSideEffect
import com.talentsarena.core.udf.UiViewState
import kotlinx.coroutines.flow.collect

/**
 * Base fragment that handles most of the unidirectional data flow logic under the hood. It is kind of only one layer
 * of inheritance to abstract the logic and share it allover the other fragments.
 */
abstract class BaseFragment<
    ActionEvent : UiActionEvent,
    ViewState : UiViewState,
    SideEffect : UiSideEffect,
    View : ViewBinding,
    > : Fragment() {

    private lateinit var viewModel: BaseViewModel<ActionEvent, ViewState, SideEffect>

    protected abstract fun initializeViewModel(): BaseViewModel<ActionEvent, ViewState, SideEffect>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): android.view.View? {
        viewBinding = initializeViewBinding()
        return viewBinding.root
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = initializeViewModel()
            lifecycleScope.launchWhenStarted {
                viewModel.uiViewState.collect { viewState ->
                    renderViewState(viewState = viewState)
                }
            }
            lifecycleScope.launchWhenStarted {
                viewModel.sideEffect.collect { sideEffect ->
                    handleSideEffect(sideEffect = sideEffect)
                }
            }
            onActivityReady(savedInstanceState = savedInstanceState)
        }
    }

    protected lateinit var viewBinding: View
    protected abstract fun initializeViewBinding(): View

    protected abstract fun onActivityReady(savedInstanceState: Bundle?)

    protected fun emitActionEvent(actionEvent: ActionEvent) {
        viewModel.emitActionEvent(actionEvent = actionEvent)
    }

    protected abstract fun renderViewState(viewState: ViewState)

    protected abstract fun handleSideEffect(sideEffect: SideEffect)
}