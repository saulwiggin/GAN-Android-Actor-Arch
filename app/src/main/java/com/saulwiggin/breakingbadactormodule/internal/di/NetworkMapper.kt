package com.saulwiggin.breakingbadactormodule.internal.di


interface NetworkMapper<NetworkObj, EntityObj> {
    fun toEntityObj(networkObj: NetworkObj):EntityObj
    fun toEntityListObj(networkObjList: List<NetworkObj>):List<EntityObj>
}