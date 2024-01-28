package zppdenga.ruonlinersxs.ui

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import zppdenga.ruonlinersxs.data.mapper.maxAmount
import zppdenga.ruonlinersxs.domain.model.Loan
import zppdenga.ruonlinersxs.domain.repository.TaRepository
import zppdenga.ruonlinersxs.domain.utils.Resource
@RequiresApi(VERSION_CODES.O)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TaRepository
) : ViewModel() {
    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()
    private var _baseLoans =  MutableStateFlow(emptyList<Loan>())

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
                    val loans = resultLoad.data?: emptyList()
                    _state.value.copy(
                        moneyList = loans,
                        isLoading = false,
                        maxSum = maxAmount(loans)
                    )
                        .updateStateUI()
                    _baseLoans.value = loans
                }
            }
        }
    }

    fun filterByMaxAmount(needSum:Int) {
        _state.value.copy(
            moneyList = _baseLoans.value.filter { it.sumOneInt>=needSum },
            neededSum = needSum
        )
            .updateStateUI()
    }

    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }
}