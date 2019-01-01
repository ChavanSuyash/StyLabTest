package com.stylab.test.injection.module

import com.stylab.test.data.api.ListService
import com.stylab.test.data.list.ListRemoteDataSource
import com.stylab.test.data.list.ListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [CoreDataModule::class])
class HomeDataModule {

    @Provides
    fun providesListRepository(listRemoteDataSource: ListRemoteDataSource) : ListRepository {
        return ListRepository.getInstance(listRemoteDataSource)
    }

    @Provides
    fun providesListRemoteDataSource(service : ListService) : ListRemoteDataSource {
        return ListRemoteDataSource(service)
    }

    @Provides
    fun providesListService(retrofit: Retrofit) : ListService {
        return retrofit.create(ListService::class.java)
    }
}