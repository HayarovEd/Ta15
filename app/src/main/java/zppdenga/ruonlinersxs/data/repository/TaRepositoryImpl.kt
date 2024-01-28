package zppdenga.ruonlinersxs.data.repository

import zppdenga.ruonlinersxs.data.mapper.mapToLoan
import zppdenga.ruonlinersxs.data.remote.ApiTa
import zppdenga.ruonlinersxs.domain.model.Loan
import zppdenga.ruonlinersxs.domain.repository.TaRepository
import zppdenga.ruonlinersxs.domain.utils.Resource
import javax.inject.Inject

class TaRepositoryImpl @Inject constructor(private val apiTa: ApiTa) : TaRepository {
    override suspend fun getRemoteData(): Resource<List<Loan>> {
        return try {
            val result = apiTa.getData()
            Resource.Success(
                result.mapToLoan()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}