package sh4k4w4t.github.io.roomdatabasetest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.adapter.book.BookAdapter;
import sh4k4w4t.github.io.roomdatabasetest.adapter.book.BookDataController;
import sh4k4w4t.github.io.roomdatabasetest.adapter.book.BookFragmentInterface;
import sh4k4w4t.github.io.roomdatabasetest.adapter.writer.WriterDataController;
import sh4k4w4t.github.io.roomdatabasetest.databinding.FragmentSecondBinding;
import sh4k4w4t.github.io.roomdatabasetest.model.Book;
import sh4k4w4t.github.io.roomdatabasetest.repo.BookRepository;

public class BookFragment extends Fragment implements BookFragmentInterface {

    private FragmentSecondBinding binding;
    String bookName,bookPrice;

    WriterDataController writerDataController;
    BookDataController bookDataController;
    BookRepository bookRepository;
    BookAdapter adapter;

    List<Book> bookList= new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        bookRepository= new BookRepository(getActivity().getApplication());
        writerDataController= WriterDataController.getInstance();
        bookDataController= BookDataController.getInstance();
        bookDataController.setBookFragmentInterface(this);

        bookList= bookRepository.GetBookListByWriterId(writerDataController.getCurrentWriter().getId());

        binding.allBookRecyclerView.setHasFixedSize(true);
        binding.allBookRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter= new BookAdapter(bookList);
        binding.allBookRecyclerView.setAdapter(adapter);
        
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                DeleteBook(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.allBookRecyclerView);

        binding.addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookName= binding.bookName.getText().toString().trim();
                bookPrice=binding.bookPrice.getText().toString().trim();

                if (bookName.isEmpty() || bookPrice.isEmpty()){
                    Toast.makeText(getActivity(), "Fill up all info", Toast.LENGTH_SHORT).show();
                }else {
                    Book book= new Book(bookName,bookPrice,writerDataController.getCurrentWriter().getId());
                    InsertBook(book);
                    bookList.add(book);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        return binding.getRoot();
    }

    private void DeleteBook(int adapterPosition) {
        Book book= bookList.get(adapterPosition);
        bookRepository.RemoveBook(book);
        bookList.remove(book);
        adapter.notifyDataSetChanged();
    }

    private void InsertBook(Book book) {
        bookRepository.AddBook(book);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onBookItemClick(Book book) {
        bookDataController.setCurrentBook(book);
//        Toast.makeText(getActivity(), ""+book.getBookName(), Toast.LENGTH_SHORT).show();
    }
}