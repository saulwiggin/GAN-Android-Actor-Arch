package com.saulwiggin.breakingbadactormodule.internal.di

import com.saulwiggin.breakingbadactormodule.data.db.ActorEntity

interface EntityMapper<EntityObject, ModelObject> {
    fun toModelObject(entityObject: EntityObject): ModelObject
    fun toModelListObject(actorEntityList: List<ActorEntity>): List<ModelObject>
}