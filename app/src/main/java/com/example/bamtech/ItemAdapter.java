package com.example.bamtech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public static final int SPAN_COUNT_ONE = 1;
    public static final int SPAN_COUNT_THREE = 3 ;

    private static final int VIEW_TYPE_lIST = 1 ;
    private static final int VIEW_TYPE_GRID = 2 ;

    private List<Items> mItems;
    private GridLayoutManager mLayoutManager;

    public ItemAdapter( List<Items>items, GridLayoutManager layoutManager) {
        mItems = items;
        mLayoutManager = layoutManager;
    }


    public int getItemViewType(int position){
        int spanCount = mLayoutManager.getSpanCount();
        if (spanCount == SPAN_COUNT_ONE ){
            return VIEW_TYPE_lIST;
        }else {
            return VIEW_TYPE_GRID;
        }
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_lIST){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        }
        return new ItemViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Items items = mItems.get( position % 4 );
        holder.title.setText(items.getTitle());
        holder.iv.setImageResource(items.getImgResId());
        if (getItemViewType(position) == VIEW_TYPE_lIST){
            holder.info.setText(items.getLikes() + "likes ‧ "+ items.getComments() + "comments");
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class ItemViewHolder extends  RecyclerView.ViewHolder{
        ImageView iv ;
        TextView title;
        TextView info;

        ItemViewHolder(View itemView , int viewType ){
            super(itemView);
            if (viewType == VIEW_TYPE_lIST) {
                iv = (ImageView) itemView.findViewById(R.id.image_list);
                title = (TextView) itemView.findViewById(R.id.title_list);
                info = (TextView) itemView.findViewById(R.id.tv_info);
            }else {
                iv = (ImageView) itemView.findViewById(R.id.image_grid);
                title = (TextView) itemView.findViewById(R.id.title_grid);
            }
        }

    }
}
