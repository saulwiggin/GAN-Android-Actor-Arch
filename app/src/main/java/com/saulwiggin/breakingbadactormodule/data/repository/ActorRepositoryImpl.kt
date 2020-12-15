package com.saulwiggin.breakingbadactormodule.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.saulwiggin.breakingbadactormodule.data.datasource.ActorDataSource
import com.saulwiggin.breakingbadactormodule.data.db.ActorDatabase
import com.saulwiggin.breakingbadactormodule.data.db.ActorEntityMapper
import com.saulwiggin.breakingbadactormodule.data.network.ActorNetwork
import com.saulwiggin.breakingbadactormodule.data.network.ActorNetworkMapper
import com.saulwiggin.breakingbadactormodule.model.ActorModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ActorRepositoryImpl(
    val actorDataSource: ActorDataSource,
    val actorDatabase: ActorDatabase
) : ActorRepository {
    private val TAG = "BreakingBad"
    val dao = actorDatabase.actorDao()

    private val _actorList = MutableLiveData<List<ActorModel>>()
    override val actorList: LiveData<List<ActorModel>> = _actorList

    private val _actorFilteredList = MutableLiveData<List<ActorModel>>()
    override val actorFilteredList: LiveData<List<ActorModel>> = _actorFilteredList

    init {
        actorDataSource.actorList.observeForever {
            persistList(it)
        }
    }

    override suspend fun refreshActorList() {
        actorDataSource.fetchActorList()
        dao.getActors().observeForever(Observer {
            _actorList.value = ActorEntityMapper().toModelListObject(it)
        })
    }

    private fun persistList(actorNetworkList: List<ActorNetwork>) {
        GlobalScope.launch(Dispatchers.IO) {
            val actorNetworkList = ActorNetworkMapper().toEntityListObj(actorNetworkList)
            dao.addActors(actorNetworkList)
        }
    }

    override suspend fun getActorsBySeason(season: Int) {
        dao.getActorsBySeason("%$season%").observeForever(Observer {
            _actorFilteredList.value = ActorEntityMapper().toModelListObject(it)
        })
    }
}