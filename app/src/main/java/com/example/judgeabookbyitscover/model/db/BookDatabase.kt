package com.example.judgeabookbyitscover.model.db

import android.content.Context
import androidx.room.*
import com.example.judgeabookbyitscover.model.datamodels.Book

/**
 * This abstract class acts as a database holder and returns one instance of a BookDatabase
 */
@Database(entities = [(Book::class)], version = 2)
//@TypeConverters(BookAttributesConverter::class)
abstract class BookDatabase : RoomDatabase() {
    abstract val bookDao: BookDao

    companion object {

        // For Singleton  instantiation
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getInstance(context: Context): BookDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            BookDatabase::class.java,
                            "books"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}