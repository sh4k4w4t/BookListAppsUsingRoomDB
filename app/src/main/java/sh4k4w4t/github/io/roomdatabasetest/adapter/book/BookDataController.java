package sh4k4w4t.github.io.roomdatabasetest.adapter.book;

import sh4k4w4t.github.io.roomdatabasetest.model.Book;

public class BookDataController {
    public static BookDataController instance;
    public static BookDataController getInstance(){
        if (instance == null){
            instance= new BookDataController();
        }
        return instance;
    }

    BookFragmentInterface bookFragmentInterface;
    Book currentBook;

    public BookFragmentInterface getBookFragmentInterface() {
        return bookFragmentInterface;
    }

    public void setBookFragmentInterface(BookFragmentInterface bookFragmentInterface) {
        this.bookFragmentInterface = bookFragmentInterface;
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }
}
