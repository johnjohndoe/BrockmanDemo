package info.metadude.android.brockman.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import info.metadude.java.library.brockman.models.Stream;

public class StreamsAdapter extends RecyclerView.Adapter<StreamViewHolder> {

    protected List<Stream> streams;

    public StreamsAdapter(List<Stream> streams) {
        this.streams = streams;
    }

    @Override
    public StreamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View streamViewItem = layoutInflater.inflate(R.layout.stream_item, parent, false);
        return new StreamViewHolder(streamViewItem);
    }

    @Override
    public void onBindViewHolder(StreamViewHolder holder, int position) {
        Stream stream = streams.get(position);
        holder.bind(stream);
    }

    @Override
    public int getItemCount() {
        return streams.size();
    }

}
