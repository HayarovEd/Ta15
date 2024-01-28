package zppdenga.ruonlinersxs.domain.repository

import zppdenga.ruonlinersxs.domain.model.Loan
import zppdenga.ruonlinersxs.domain.utils.Resource

interface TaRepository {
    suspend fun getRemoteData(): Resource<List<Loan>>
}