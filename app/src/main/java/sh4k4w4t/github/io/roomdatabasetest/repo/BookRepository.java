package sh4k4w4t.github.io.roomdatabasetest.repo;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.dao.BookDao;
import sh4k4w4t.github.io.roomdatabasetest.db.LibraryDatabase;
import sh4k4w4t.github.io.roomdatabasetest.model.Book;

public class BookRepository {
    public BookDao bookDao;
    List<Book> bookList = new ArrayList<>();

    public BookRepository(Application application) {
        LibraryDatabase libraryDatabase = LibraryDatabase.getInstance(application);
        bookDao = libraryDatabase.bookDao();
    }

    public void addBook(Book book) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.InsertBook(book);
            }
        });
    }



    public void removeBook(Book book) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.DeleteBook(book);
            }
        });
    }



    public void updateBook(Book book) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.UpdateBook(book);
            }
        });
    }



    public List<Book> getBookList(int writerId) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookList = bookDao.GetAllBookByWriterId(writerId);
            }
        });
        return bookList;
    }
}
