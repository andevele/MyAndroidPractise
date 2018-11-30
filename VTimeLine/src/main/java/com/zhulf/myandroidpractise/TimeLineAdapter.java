package com.zhulf.myandroidpractise;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.ViewHolder> {

    private List<DataBean> dataList;

    public TimeLineAdapter(List<DataBean> data) {
        this.dataList = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataBean dataBean = dataList.get(position);
        holder.time.setText(dataBean.getTime());
        holder.address.setText(dataBean.getAddress());

    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            this.time = itemView.findViewById(R.id.tv_time);
            this.address = itemView.findViewById(R.id.tv_address);
        }
    }
}
