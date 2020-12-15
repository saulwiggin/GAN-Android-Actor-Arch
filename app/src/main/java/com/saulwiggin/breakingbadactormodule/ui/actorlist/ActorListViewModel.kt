package com.saulwiggin.breakingbadactormodule.ui.actorlist

import android.util.Log
import androidx.lifecycle.*
import com.saulwiggin.breakingbadactormodule.data.repository.ActorRepository
import com.saulwiggin.breakingbadactormodule.internal.di.LoadingState
import kotlinx.coroutines.launch
import java.lang.Exception

class ActorListViewModel(val repository: ActorRepository): ViewModel() {
    private val TAG = "Breaking Bad"

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState:LiveData<LoadingState> = _loadingState


    val actorList = repository.actorList
    val filteredActorList = repository.actorFilteredList

    init {
        refreshActorList()
    }

    private fun refreshActorList() {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                repository.refreshActorList()
                _loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
                Log.e(TAG, "refreshActorList: ${e.message}", )
            }
        }
    }

    fun retrieveActorsBySeason(season: Int) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                repository.getActorsBySeason(season)
                _loadingState.value = LoadingState.LOADED

            } catch (e: Exception) {
                _loadingState.value = LoadingState.error(e.message)
                Log.e(TAG, "refreshActorList: ${e.message}",)
            }
        }
    }

    fun retrieveDataUsingFlow(){
        TODO("Retrieve data from room database using flow instead of livedata")
    }
}