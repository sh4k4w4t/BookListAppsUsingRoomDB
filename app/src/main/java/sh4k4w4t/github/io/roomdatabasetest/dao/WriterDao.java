package sh4k4w4t.github.io.roomdatabasetest.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.model.Writer;

@Dao
public interface WriterDao {

    @Insert
    void InsertWriter(Writer writer);

    @Delete
    void DeleteWriter(Writer writer);

    @Update
    void UpdateWriter(Writer writer);

    @Query("select * from Writer order by id ASC")
    List<Writer> GetAllWriters();
}
