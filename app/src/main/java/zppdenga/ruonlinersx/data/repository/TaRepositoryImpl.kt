package zppdenga.ruonlinersx.data.repository

import zppdenga.ruonlinersx.data.mapper.mapToLoan
import zppdenga.ruonlinersx.data.remote.ApiTa
import zppdenga.ruonlinersx.domain.model.Loan
import zppdenga.ruonlinersx.domain.repository.TaRepository
import zppdenga.ruonlinersx.domain.utils.Resource
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