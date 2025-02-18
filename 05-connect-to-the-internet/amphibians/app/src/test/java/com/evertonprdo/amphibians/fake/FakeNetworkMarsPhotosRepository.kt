package com.evertonprdo.amphibians.fake

import com.evertonprdo.amphibians.data.AmphibiansRepository
import com.evertonprdo.amphibians.model.Amphibian

class FakeNetworkAmphibiansRepository : AmphibiansRepository {

    override suspend fun getAmphibians(): List<Amphibian> {
        return FakeDataSource.amphibianList
    }
}