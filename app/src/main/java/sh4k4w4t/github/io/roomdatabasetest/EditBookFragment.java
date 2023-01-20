package sh4k4w4t.github.io.roomdatabasetest;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import sh4k4w4t.github.io.roomdatabasetest.adapter.book.BookDataController;
import sh4k4w4t.github.io.roomdatabasetest.databinding.FragmentEditBookBinding;
import sh4k4w4t.github.io.roomdatabasetest.model.Book;
import sh4k4w4t.github.io.roomdatabasetest.repo.BookRepository;

public class EditBookFragment extends Fragment {
    FragmentEditBookBinding binding;
    String bookTitle, bookPrice;

    BookDataController bookDataController;
    BookRepository bookRepository;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEditBookBinding.inflate(getLayoutInflater());

        bookRepository= new BookRepository(getActivity().getApplication());
        bookDataController= BookDataController.getInstance();

        binding.bookNameEditText.setText(bookDataController.getCurrentBook().getBookName()+"");
        binding.editPriceEditText.setText(bookDataController.getCurrentBook().getBookPrice()+"");

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookTitle= binding.bookNameEditText.getText().toString().trim();
                bookPrice= binding.editPriceEditText.getText().toString().trim();

                if (bookPrice.isEmpty()||bookTitle.isEmpty()){
                    Toast.makeText(getActivity(), "Please Fill Up All Info", Toast.LENGTH_SHORT).show();
                }else {
                    Book book= new Book(bookTitle,bookTitle,bookDataController.getCurrentBook().getId());
                    UpdateBookInformation(book);
                }
            }
        });

        return binding.getRoot();
    }

    private void UpdateBookInformation(Book book) {
        bookRepository.UpdateBook(book);
        Toast.makeText(getActivity(), "Update Book Information", Toast.LENGTH_SHORT).show();
//        NavHostFragment.findNavController(EditBookFragment.this)
//                .navigate(R.id.action_EditBookFragment_to_SecondFragment);
    }
}