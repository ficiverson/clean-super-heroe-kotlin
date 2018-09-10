package test.kotlin.clean.ficiverson.cache.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import test.kotlin.clean.ficiverson.cache.database.dao.DaoAccess
import test.kotlin.clean.ficiverson.cache.model.SuperHeroeDatabaseEntity

@Database(entities = [(SuperHeroeDatabaseEntity::class)], version = 1, exportSchema = false)
abstract class SuperHeroesDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess
}