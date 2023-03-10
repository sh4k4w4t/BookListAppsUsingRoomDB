package sh4k4w4t.github.io.roomdatabasetest.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String bookName;
    public String bookPrice;
    public int writerId;

    public Book() {
    }

    @Ignore
    public Book(String bookName, String bookPrice, int writerId) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.writerId = writerId;
    }

    @Ignore
    public Book(int id, String bookName, String bookPrice, int writerId) {
        this.id = id;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.writerId = writerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
}
