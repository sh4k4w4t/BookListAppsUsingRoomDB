package sh4k4w4t.github.io.roomdatabasetest.repo;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public void AddBook(Book book) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.InsertBook(book);
            }
        });
    }


    public void RemoveBook(Book book) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.DeleteBook(book);
            }
        });
    }

    public void UpdateBook(Context context, Book book) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
//                bookDao.UpdateBook(book);
                LibraryDatabase libraryDatabase= LibraryDatabase.getInstance(context);
                libraryDatabase.bookDao().UpdateBook(book);
            }
        });
    }

//    public void UpdateBook(Book book) {
//        new UpdateBookTask(bookDao).execute(book);
//    }
//    public static class UpdateBookTask extends AsyncTask<Book,Void,Void>{
//        BookDao bookDao;
//        public UpdateBookTask(BookDao bookDao) {
//            this.bookDao = bookDao;
//        }
//        @Override
//        protected Void doInBackground(Book... books) {
//            bookDao.UpdateBook(books[0]);
//            return null;
//        }
//    }



    public List<Book> GetBookListByWriterId(int writerId) {
        try {
            bookList= new GetAllBookByWriterIdTask(bookDao).execute(writerId).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static class GetAllBookByWriterIdTask extends AsyncTask<Integer,Void,List<Book>>{
        BookDao dao;
        public GetAllBookByWriterIdTask(BookDao dao) {
            this.dao = dao;
        }
        @Override
        protected List<Book> doInBackground(Integer... integers) {
            return dao.GetAllBookByWriterId(integers[0]);
        }
    }
}
