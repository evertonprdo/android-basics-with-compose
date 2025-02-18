package com.evertonprdo.amphibians.fake

import com.evertonprdo.amphibians.model.Amphibian
import com.evertonprdo.amphibians.network.AmphibiansApiService

class FakeAmphibiansApiService : AmphibiansApiService {

    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibianList
    }
}