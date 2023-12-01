package zppdenga.creditplus.domain.repository

import zppdenga.creditplus.domain.model.Loan
import zppdenga.creditplus.domain.utils.Resource

interface TaRepository {
    suspend fun getRemoteData(): Resource<List<Loan>>
}