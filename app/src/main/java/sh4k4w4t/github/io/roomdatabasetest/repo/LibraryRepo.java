package sh4k4w4t.github.io.roomdatabasetest.repo;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sh4k4w4t.github.io.roomdatabasetest.dao.BookDao;
import sh4k4w4t.github.io.roomdatabasetest.dao.WriterDao;
import sh4k4w4t.github.io.roomdatabasetest.db.LibraryDatabase;
import sh4k4w4t.github.io.roomdatabasetest.model.Book;
import sh4k4w4t.github.io.roomdatabasetest.model.Writer;

public class LibraryRepo {
    public BookDao bookDao;
    public WriterDao writerDao;

    List<Book> bookList = new ArrayList<>();
    List<Writer> writerList = new ArrayList<>();

    public LibraryRepo(Application application) {
        LibraryDatabase libraryDatabase = LibraryDatabase.getInstance(application);
        bookDao = libraryDatabase.bookDao();
        writerDao = libraryDatabase.writerDao();
    }

    //Writer repo section----------------------------------------------------------------

    public void addWriter(Writer writer) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                writerDao.InsertWriter(writer);
            }
        });
    }




    public void removeWriter(Writer writer) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                writerDao.DeleteWriter(writer);
            }
        });
    }



    public void updateWriter(Writer writer) {
        LibraryDatabase.databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                writerDao.UpdateWriter(writer);
            }
        });
    }




    public List<Writer> getAllWriters(){
        try {
            writerList= new GelAllWriterTask(writerDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return writerList;
    }
    private static class GelAllWriterTask extends AsyncTask<Void,Void,List<Writer>> {
        WriterDao dao;
        public GelAllWriterTask(WriterDao writerDao) {
            this.dao = writerDao;
        }

        @Override
        protected List<Writer> doInBackground(Void... voids) {
            return dao.GetAllWriters();
        }
    }










    //Book repo section------------------------------------------------------------------

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
