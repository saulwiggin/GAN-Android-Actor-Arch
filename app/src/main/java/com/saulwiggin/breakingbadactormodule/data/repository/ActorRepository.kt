package com.saulwiggin.breakingbadactormodule.data.repository

import androidx.lifecycle.LiveData
import com.saulwiggin.breakingbadactormodule.model.ActorModel

interface ActorRepository {

    val actorList: LiveData<List<ActorModel>>
    val actorFilteredList: LiveData<List<ActorModel>>

    suspend fun refreshActorList()
    suspend fun getActorsBySeason(season: Int)
}