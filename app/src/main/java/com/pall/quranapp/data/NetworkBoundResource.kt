package com.pall.quranapp.data

import com.pall.quranapp.domain.model.Surah
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.net.Authenticator.RequestorType

abstract class NetworkBoundResource<ResultType, RequestType> {
    val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is NetworkResponse.Success -> {
                emitAll(fetchFromNetwork(apiResponse.data).map {
                    Resource.Success(it)
                })
            }
            is NetworkResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        }
    }
    abstract fun fetchFromNetwork(data: RequestType): Flow<ResultType>
    protected abstract suspend fun createCall(): Flow<NetworkResponse<RequestType>>

    fun asFlow() : Flow<Resource<ResultType>> = result
}