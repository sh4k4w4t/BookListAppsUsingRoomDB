package sh4k4w4t.github.io.roomdatabasetest.adapter.book;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.R;
import sh4k4w4t.github.io.roomdatabasetest.model.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book= bookList.get(position);
        holder.bookName.setText(book.getBookName()+"");
        holder.bookPrice.setText(book.getBookPrice()+"");
    }

    @Override
    public int getItemCount() {
        if (bookList.size() == 0){
            return 0;
        }
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView bookName, bookPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookTitle);
            bookPrice = itemView.findViewById(R.id.bookPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            BookDataController.getInstance().getBookFragmentInterface().onBookItemClick(bookList.get(getAdapterPosition()));
        }
    }
}
