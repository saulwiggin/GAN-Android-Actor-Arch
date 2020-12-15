package com.saulwiggin.breakingbadactormodule.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.saulwiggin.breakingbadactormodule.data.network.ActorApiService
import com.saulwiggin.breakingbadactormodule.data.network.ActorNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActorDataSourceImpl(val actorApiService: ActorApiService) : ActorDataSource {
    private val TAG = "BreakingBad"
    private val _actorList = MutableLiveData<List<ActorNetwork>>()
    override val actorList: LiveData<List<ActorNetwork>> = _actorList

    override suspend fun fetchActorList() {
        _actorList.value = actorApiService.getAllActors().await()
    }
}