package codepath.com.gitreccedproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class libAdapter extends Adapter<libAdapter.ViewHolder> {

    Context context;
    public List<Item> mItems;

    //config required for img urls
    Config config;

    public libAdapter(List<Item> items) {
        mItems = items;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @NonNull
    @Override
    public libAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View searchView = inflater.inflate(R.layout.item, parent, false);
        libAdapter.ViewHolder viewHolder = new libAdapter.ViewHolder(searchView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull libAdapter.ViewHolder holder, int position) {
        //TODO: populate movie/tv images based on config
        // get the data according to position
        //Item item = mItems.get(position % mItems.size());
        // populate the views according to position
        //holder.textview1.setText(item.title);
    }

    @Override
    public int getItemCount() {
        //return mItems.size();
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textview1;
        CardView cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textview1 = itemView.findViewById(R.id.textview1);
            cardview = itemView.findViewById(R.id.cardview);
            cardview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //int position = getAdapterPosition() % mItems.size();
            //Toast.makeText(context, String.format("Clicked %s!", position), Toast.LENGTH_SHORT).show();
        }
    }
}
