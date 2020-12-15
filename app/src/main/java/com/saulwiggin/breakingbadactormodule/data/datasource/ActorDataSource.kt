package com.saulwiggin.breakingbadactormodule.data.datasource

import androidx.lifecycle.LiveData
import com.saulwiggin.breakingbadactormodule.data.network.ActorNetwork

interface ActorDataSource {

    val actorList:LiveData<List<ActorNetwork>>

    suspend fun fetchActorList()
}