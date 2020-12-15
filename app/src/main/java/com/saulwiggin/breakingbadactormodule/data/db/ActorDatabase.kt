package com.saulwiggin.breakingbadactormodule.data.db

import android.content.Context
import android.util.Log
import androidx.room.*
import com.saulwiggin.breakingbadactormodule.internal.di.Converters

@Database(entities = [ActorEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ActorDatabase:RoomDatabase() {
    abstract fun actorDao(): ActorDao

    companion object {
        private val TAG = "Breakingbad"
        @Volatile private var instance:ActorDatabase ?= null
        private val LOCK =  Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            Log.i(TAG, "invoke: createingDB")
            instance?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            ActorDatabase::class.java, "actor-db").build()
    }
}