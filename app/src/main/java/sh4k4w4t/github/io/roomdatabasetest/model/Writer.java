package sh4k4w4t.github.io.roomdatabasetest.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Writer {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String writerName;
    public String aboutWriter;

    public Writer(String writerName, String aboutWriter) {
        this.writerName = writerName;
        this.aboutWriter = aboutWriter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getAboutWriter() {
        return aboutWriter;
    }

    public void setAboutWriter(String aboutWriter) {
        this.aboutWriter = aboutWriter;
    }
}
