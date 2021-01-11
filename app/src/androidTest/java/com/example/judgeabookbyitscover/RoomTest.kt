package com.example.judgeabookbyitscover

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.judgeabookbyitscover.model.db.BookDao
import com.example.judgeabookbyitscover.model.db.BookDatabase
import com.example.judgeabookbyitscover.model.datamodels.Book
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var bookDao: BookDao
    private lateinit var db: BookDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        bookDao = db.bookDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetBook() {
        val book = Book()
        bookDao.insert(book)
        val oneBook = bookDao.getBooks()[0]
        assertEquals(oneBook.author, "test")
    }
}