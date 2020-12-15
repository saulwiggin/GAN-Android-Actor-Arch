package com.saulwiggin.breakingbadactormodule.data.network

import com.saulwiggin.breakingbadactormodule.data.db.ActorEntity
import com.saulwiggin.breakingbadactormodule.internal.di.NetworkMapper

data class ActorNetwork(
    val appearance: List<String>?,
    val char_id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val status: String
)

class ActorNetworkMapper():NetworkMapper<ActorNetwork, ActorEntity> {
    private val TAG = "BreakingBad"
    override fun toEntityObj(networkObj: ActorNetwork): ActorEntity {
        return ActorEntity(appearance = networkObj.appearance,
            id = networkObj.char_id,
            img = networkObj.img,
            name = networkObj.name,
            nickname = networkObj.nickname,
            occupation = networkObj.occupation,
            status = networkObj.status)
    }

    override fun toEntityListObj(networkObjList: List<ActorNetwork>): List<ActorEntity> {
        return networkObjList.map {
            toEntityObj(it)
        }
    }
}