package ru.denisvukolov.genesapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.denisvukolov.genesapp.OnListItemClickListener;
import ru.denisvukolov.genesapp.util.Utils;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.genesapp.R;

public class GenesAdapter extends RecyclerView.Adapter<GenesAdapter.GeneItemViewHolder> {

    private List<GeneItem> items;
    private final OnListItemClickListener<Integer> onItemClickListener;
    private Context context;

    public GenesAdapter(OnListItemClickListener<Integer> onItemClickListener) {
        this.items = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public GeneItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_item_gene, parent, false);
        return new GeneItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GeneItemViewHolder holder, int position) {
        GeneItem item = items.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(view ->
                onItemClickListener.onItemClicked(item.getId(), position)
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swapItems(List<GeneItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static class GeneItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAge;

        public GeneItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }

        public void bind(GeneItem item) {
            tvName.setText(Utils.fromHtml("<b>" + item.getSymbol() + "</b> " + item.getName()));
            tvAge.setText(Utils.fromHtml("<b>" + item.getOrigin().getPhylum() + "</b> " + item.getOrigin().getAge() + " (млн. лет)"));
        }
    }
}
