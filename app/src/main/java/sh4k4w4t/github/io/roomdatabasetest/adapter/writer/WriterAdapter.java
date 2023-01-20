package sh4k4w4t.github.io.roomdatabasetest.adapter.writer;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.R;
import sh4k4w4t.github.io.roomdatabasetest.model.Writer;

public class WriterAdapter extends RecyclerView.Adapter<WriterAdapter.viewHolder>{
    List<Writer> writerList;

    public WriterAdapter(List<Writer> writerList) {
        this.writerList = writerList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.writer_custom_layout,parent,false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Writer currentWriter= writerList.get(position);
        holder.writerName.setText(currentWriter.getWriterName()+"");
        holder.writerDescription.setText(currentWriter.getAboutWriter()+"");
    }

    @Override
    public int getItemCount() {
        if (writerList.isEmpty()){
            return 0;
        }
        return writerList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView writerName, writerDescription;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            writerName= itemView.findViewById(R.id.bookTitle);
            writerDescription= itemView.findViewById(R.id.bookDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            WriterDataController.INSTANCE.getWriterFragmentInterface().onWriterItemClick(writerList.get(getAdapterPosition()));
        }
    }
}
