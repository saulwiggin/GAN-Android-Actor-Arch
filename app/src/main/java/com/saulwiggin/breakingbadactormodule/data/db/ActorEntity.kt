package com.saulwiggin.breakingbadactormodule.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor_table")
class ActorEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val appearance: List<String>?,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val status: String
)