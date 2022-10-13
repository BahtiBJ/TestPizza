package com.bbj.testpizza.domain

import com.bbj.testpizza.data.FakeMenuRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class FetchProductsUseCaseTest{

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun isReturnSortedByTypeList() = runTest {
        val repository = FakeMenuRepositoryImpl()
        val productList = FetchProductsUseCase(repository).execute()
        val isTypeSame = productList[0].type == productList[1].type
        assertTrue(isTypeSame)
    }

}