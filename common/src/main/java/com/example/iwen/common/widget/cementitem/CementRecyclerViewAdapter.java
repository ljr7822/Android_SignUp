package com.example.iwen.common.widget.cementitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.R;
import com.example.iwen.common.utils.BitmapUtil;
import com.example.iwen.common.widget.Interface.ItemTouchHelperAdapter;

import java.util.List;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * 首页公告的Adapter
 * author : Iwen大大怪
 * create : 11-23 023 15:28
 */
public class CementRecyclerViewAdapter extends RecyclerView.Adapter<CementRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<Cements> cementsList;
    private Context context;
    private MaterialDialog dialog;
    private int truePosition, itemPosition;

    public CementRecyclerViewAdapter(List<Cements> cementsList, Context context) {
        this.cementsList = cementsList;
        this.context = context;
    }

    //自定义ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView cement_title;
        TextView cement_desc;
        TextView cement_time;
        //ImageView cement_card_bg;

        public ViewHolder(View itemView) {
            super(itemView);
            cement_title = itemView.findViewById(R.id.cement_title);
            cement_desc = itemView.findViewById(R.id.cement_desc);
            cement_time = itemView.findViewById(R.id.cement_time);
            //cement_card_bg = itemView.findViewById(R.id.cement_card_bg);
        }
    }

    @NonNull
    @Override
    public CementRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CementRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.cement_title.setText(cementsList.get(cementsList.size()-1-position).getTitle());
        holder.cement_desc.setText(cementsList.get(cementsList.size()-1-position).getDesc());
        holder.cement_time.setText(cementsList.get(cementsList.size()-1-position).getTime());
        //holder.cement_card_bg.setImageBitmap(BitmapUtil.readBitMap(context,cementsList.get(cementsList.size()-1-position).getImgId()));
    }

    @Override
    public int getItemCount() {
        return cementsList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void removeItem(int position) {

    }
}
