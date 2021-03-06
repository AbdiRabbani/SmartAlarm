package com.abdi.smartalarm.Data.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdi.smartalarm.Data.Alarm

@Database(entities = [Alarm::class], version = 2)
abstract class AlarmDB : RoomDatabase(){
    abstract fun alarmDao() : AlarmDao


    companion object{
        @Volatile
        var instance: AlarmDB? = null

        @JvmStatic
        fun  getDataBase(context: Context) : AlarmDB{
            if (instance == null)  {
                synchronized(AlarmDB::class){
                    instance = Room.databaseBuilder(
                        context, AlarmDB::class.java, "Alarm.db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance as AlarmDB
        }
    }
}