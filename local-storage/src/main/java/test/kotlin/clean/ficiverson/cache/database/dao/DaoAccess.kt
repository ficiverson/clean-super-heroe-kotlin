package test.kotlin.clean.ficiverson.cache.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import test.kotlin.clean.ficiverson.cache.model.SuperHeroeDatabaseEntity


@Dao
interface DaoAccess {

    @Insert
    fun insertSuperHeroe(superHeroe: SuperHeroeDatabaseEntity)

    @Query("SELECT * FROM SuperHeroeDatabaseEntity")
    fun getAllSuperHeroes(): List<SuperHeroeDatabaseEntity>
}