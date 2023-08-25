package com.example.countries.domain.use_cases

import com.example.countries.common.Resource
import com.example.countries.data.dto.toCountry
import com.example.countries.domain.model.Country
import com.example.countries.domain.model.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading()) // emitted immediately when invoke function is called
            val countries = repository.getAllCountries() // data to emitted in case of success
            emit(Resource.Success(data = countries.map { it.toCountry() })) // converts every CountryDto into Country
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