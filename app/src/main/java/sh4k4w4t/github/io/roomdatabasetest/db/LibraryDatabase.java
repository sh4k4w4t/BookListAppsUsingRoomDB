package sh4k4w4t.github.io.roomdatabasetest.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sh4k4w4t.github.io.roomdatabasetest.dao.BookDao;
import sh4k4w4t.github.io.roomdatabasetest.dao.WriterDao;
import sh4k4w4t.github.io.roomdatabasetest.model.Book;
import sh4k4w4t.github.io.roomdatabasetest.model.Writer;

@Database(entities = {Book.class, Writer.class},version = 6,exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
    public abstract WriterDao writerDao();

    public static volatile LibraryDatabase INSTANCE;

    public static final int NUMBER_OF_THREADS =4;
    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LibraryDatabase getInstance(final Context context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), LibraryDatabase.class, "LIBRARYDATABASE")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
