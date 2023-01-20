package sh4k4w4t.github.io.roomdatabasetest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.roomdatabasetest.adapter.writer.WriterAdapter;
import sh4k4w4t.github.io.roomdatabasetest.adapter.writer.WriterDataController;
import sh4k4w4t.github.io.roomdatabasetest.adapter.writer.WriterFragmentInterface;
import sh4k4w4t.github.io.roomdatabasetest.databinding.FragmentFirstBinding;
import sh4k4w4t.github.io.roomdatabasetest.model.Writer;
import sh4k4w4t.github.io.roomdatabasetest.repo.WriterRepository;

public class WriterFragment extends Fragment implements WriterFragmentInterface {

    private FragmentFirstBinding binding;
    WriterRepository writerRepository;

    WriterAdapter writerAdapter;
    List<Writer> allWriter = new ArrayList<>();
    WriterDataController writerDataController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        ToolbarMethod(binding);

        writerRepository = new WriterRepository(getActivity().getApplication());

        binding.writerRecycleView.setHasFixedSize(true);
        binding.writerRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allWriter= writerRepository.getAllWriters();
        writerAdapter= new WriterAdapter(allWriter);
        binding.writerRecycleView.setAdapter(writerAdapter);

        writerDataController= WriterDataController.getInstance();
        writerDataController.setWriterFragmentInterface(this);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                DeleteWriter(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.writerRecycleView);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.add_writer_custom_dialogue);
                EditText writerName = dialog.findViewById(R.id.writerName);
                EditText aboutWriter = dialog.findViewById(R.id.aboutWriter);
                Button addWriterButton = dialog.findViewById(R.id.addWriterButton);
                addWriterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (writerName.getText().toString().trim().isEmpty() || aboutWriter.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getActivity(), "Fill up all info", Toast.LENGTH_SHORT).show();
                        } else {
                            Writer writer = new Writer(writerName.getText().toString().trim(), aboutWriter.getText().toString().trim());
                            InsertWriter(writer);
                            dialog.dismiss();
                            Toast.makeText(getActivity(), writerName.getText().toString().trim() + " added.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

        return binding.getRoot();
    }

    private void ToolbarMethod(FragmentFirstBinding binding) {
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hello 123", Toast.LENGTH_SHORT).show();
            }
        });

        binding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.action_settings){
                    Toast.makeText(getActivity(), "Setting section", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void DeleteWriter(int adapterPosition) {
        Writer writer= allWriter.get(adapterPosition);
        writerRepository.removeWriter(writer);
        allWriter.remove(writer);
        writerAdapter.notifyDataSetChanged();
    }

    private void InsertWriter(Writer writer) {
        writerRepository.addWriter(writer);
        allWriter.add(writer);
        writerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onWriterItemClick(Writer writer) {
        writerDataController.setCurrentWriter(writer);
        NavHostFragment.findNavController(WriterFragment.this)
        .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }
}