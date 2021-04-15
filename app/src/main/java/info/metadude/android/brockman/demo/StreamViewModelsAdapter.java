package info.metadude.android.brockman.demo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import info.metadude.android.brockman.demo.databinding.StreamItemBinding;
import info.metadude.android.brockman.demo.models.StreamViewModel;

class StreamViewModelsAdapter extends RecyclerView.Adapter<StreamViewModelHolder> {

    private final List<StreamViewModel> streamViewModels;

    StreamViewModelsAdapter(List<StreamViewModel> streamViewModels) {
        this.streamViewModels = streamViewModels;
    }

    @NonNull
    @Override
    public StreamViewModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        StreamItemBinding binding = StreamItemBinding.inflate(layoutInflater, parent, false);
        return new StreamViewModelHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamViewModelHolder holder, int position) {
        StreamViewModel streamViewModel = streamViewModels.get(position);
        holder.bind(streamViewModel);
    }

    @Override
    public int getItemCount() {
        return streamViewModels.size();
    }

}
