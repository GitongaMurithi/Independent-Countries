package com.example.countries.domain.use_cases

import com.example.countries.common.Resource
import com.example.countries.data.dto1.toCountryInfo
import com.example.countries.domain.model.CountryInfo
import com.example.countries.domain.model.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(name: String): Flow<Resource<List<CountryInfo>>> = flow {
        try {
            emit(Resource.Loading()) // emitted immediately when invoke function is called
            val country =
                repository.getCountryByName(name) // data to be emitted in case of success. converts every CountryDto into Country

            emit(Resource.Success(data = country.map { it.toCountryInfo() }))

        } catch (exception: IOException) {
            emit(
                Resource.Error(
                    message = exception.localizedMessage
                        ?: "Could not reach the server; check your internet connection"
                )
            )

        } catch (exception: HttpException) {
            emit(
                Resource.Error(
                    message = exception.localizedMessage ?: "An unexpected error occurred!"
                )
            )
        }
    }
}