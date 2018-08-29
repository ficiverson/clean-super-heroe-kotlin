package test.kotlin.clean.ficiverson.cache.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by f.souto.gonzalez on 24/08/2018.
 */

@Entity
class SuperHeroeDatabaseEntity(
    @PrimaryKey
    val name: String,
    val title: String,
    val avatar: String
)
