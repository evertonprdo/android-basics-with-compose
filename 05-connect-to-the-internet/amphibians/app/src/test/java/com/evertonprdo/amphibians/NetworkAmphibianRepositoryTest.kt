package com.evertonprdo.amphibians

import com.evertonprdo.amphibians.data.NetworkAmphibiansRepository
import com.evertonprdo.amphibians.fake.FakeAmphibiansApiService
import com.evertonprdo.amphibians.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class NetworkAmphibiansRepositoryTest {
    @Test
    fun networkAmphibiansRepository_getAmphibianList() = runTest {
        val repository = NetworkAmphibiansRepository(FakeAmphibiansApiService())

        Assert.assertEquals(
            FakeDataSource.amphibianList,
            repository.getAmphibians()
        )
    }
}