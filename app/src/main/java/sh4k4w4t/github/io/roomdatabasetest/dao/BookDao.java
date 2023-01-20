package sh4k4w4t.github.io.roomdatabasetest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import static androidx.room.OnConflictStrategy.REPLACE;

import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.model.Book;

@Dao
public interface BookDao {

    @Insert
    void InsertBook(Book book);

    @Delete
    void DeleteBook(Book book);

    @Update(onConflict=REPLACE)
    void UpdateBook(Book book);

    @Query("select * from Book where writerId Like :writerIdNo")
    List<Book> GetAllBookByWriterId(int writerIdNo);

}
