package com.example.dell_pc.meizhi.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell_pc.meizhi.Model.BaseModel;
import com.example.dell_pc.meizhi.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wbin on 2016/8/19.
 */
public class BaseAdapter extends RecyclerView.Adapter {
    List<BaseModel> list;

    public interface OnItemClickListener {
        void onItemClick(View view, int position, String url, String title);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        BaseModel item = list.get(position);
        ((MyHolder) holder).textView.setText(item.getTitle());
        Glide.with(holder.itemView.getContext()).load(item.getImgUrl()).into(((MyHolder) holder).img);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition(),
                            list.get(position).getImgUrl(), list.get(position).getTitle());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setList(List<BaseModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView)
        ImageView img;
        @Bind(R.id.titleTv)
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

