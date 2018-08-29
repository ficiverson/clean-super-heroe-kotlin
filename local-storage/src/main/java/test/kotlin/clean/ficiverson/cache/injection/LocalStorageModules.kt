package test.kotlin.clean.ficiverson.cache.injection

import android.arch.persistence.room.Room
import android.content.Context
import org.koin.dsl.module.applicationContext
import test.kotlin.clean.ficiverson.cache.database.SuperHeroesDatabase

class LocalStorageModules(context: Context) {

    companion object {
        private const val DATABASE_NAME = "super_heroes_database"
    }

    val module = applicationContext {
        bean { superHeroesDatabase(context) }
    }

    private fun superHeroesDatabase(context: Context): SuperHeroesDatabase =
        Room.databaseBuilder(context, SuperHeroesDatabase::class.java, DATABASE_NAME) //We will not add migration by this time
            .build()
}