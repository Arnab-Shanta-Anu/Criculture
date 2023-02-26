package com.arnab.criculture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arnab.criculture.db.dao.ICricultureDao
import com.arnab.criculture.models.teams.TeamData

@Database(entities = [TeamData::class], version =1 , exportSchema = false)
abstract class CricultureDatabase:RoomDatabase() {
    abstract fun cricultureDao(): ICricultureDao

    companion object {
        @Volatile
        private var INSTANCE: CricultureDatabase? = null


        fun getDatabaseInstance(context: Context): CricultureDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CricultureDatabase::class.java,
                    "criculture_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}