package com.saulwiggin.breakingbadactormodule.data.db

import com.saulwiggin.breakingbadactormodule.internal.di.EntityMapper
import com.saulwiggin.breakingbadactormodule.model.ActorModel

class ActorEntityMapper(): EntityMapper<ActorEntity, ActorModel> {
    override fun toModelObject(actorEntity: ActorEntity): ActorModel {
        return ActorModel(id=actorEntity.id,
            appearance = actorEntity.appearance,
            img = actorEntity.img,
            name = actorEntity.name,
            nickname = actorEntity.nickname,
            occupation = actorEntity.occupation,
            status = actorEntity.status

        )
    }

    override fun toModelListObject(actorEntityList: List<ActorEntity>): List<ActorModel> {
        return actorEntityList.map {
            toModelObject(it)
        }
    }
}