package com.saulwiggin.breakingbadactormodule.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addActors(actorList: List<ActorEntity>)

    @Query("select * from `actor_table`")
    fun getActors():LiveData<List<ActorEntity>>

    @Query("select * from `actor_table` where appearance like :season ORDER BY name")
    fun getActorsBySeason(season: String):LiveData<List<ActorEntity>>
}