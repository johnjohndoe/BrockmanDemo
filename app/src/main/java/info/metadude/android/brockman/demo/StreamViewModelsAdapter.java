package info.metadude.android.brockman.demo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
        View streamViewItem = layoutInflater.inflate(R.layout.stream_item, parent, false);
        return new StreamViewModelHolder(streamViewItem);
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
