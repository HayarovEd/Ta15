package zppdenga.ruonlinersx.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import zppdenga.ruonlinersx.domain.repository.TaRepository
import zppdenga.ruonlinersx.domain.utils.Resource

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TaRepository
) : ViewModel() {
    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            when (val resultLoad = repository.getRemoteData()) {
                is Resource.Error -> {
                    _state.value.copy(
                        error = resultLoad.message,
                        isLoading = false
                    )
                        .updateStateUI()
                }
                is Resource.Success -> {
                    _state.value.copy(
                        moneyList = resultLoad.data?: emptyList(),
                        isLoading = false
                    )
                        .updateStateUI()
                }
            }
        }
    }

    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }
}