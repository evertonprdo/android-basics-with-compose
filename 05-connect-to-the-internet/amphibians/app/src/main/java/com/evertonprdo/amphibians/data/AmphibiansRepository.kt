package com.evertonprdo.amphibians.data

import com.evertonprdo.amphibians.model.Amphibian
import com.evertonprdo.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<Amphibian> =
        amphibiansApiService.getAmphibians()
}