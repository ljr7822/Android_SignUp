package com.example.iwen.common.widget.message;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.R;
import com.example.iwen.common.utils.BitmapUtil;
import com.example.iwen.common.widget.Interface.ItemTouchHelperAdapter;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.Collections;
import java.util.List;

import es.dmoral.toasty.Toasty;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * @author : Iwen大大怪
 * create : 11-22 022 20:09
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Messages> messagesList;
    private Context context;
    private int truePosition, itemPosition;
    private MaterialDialog dialog;

    public MessageAdapter(List<Messages> messagesList, Context context) {
        this.messagesList = messagesList;
        this.context = context;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(messagesList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemRangeChanged(fromPosition, toPosition);
        return true;
    }

    @Override
    public void removeItem(int position) {
        truePosition = messagesList.size() - 1 - position;
        itemPosition = position;
        popAlertDialog();
    }

    // 自定义ViewHolder类
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView message_title; // 消息标题
        TextView message_desc; // 消息详情
        TextView message_date; // 消息时间
        TextView message_tag; // 签到状态
        ImageView card_background; // 卡片背景
        TextView isRepeat; // 是否单次
        TimelineView timelineView; // 时间轴线

        // 获取视图
        public ViewHolder(View itemView) {
            super(itemView);
            message_title = itemView.findViewById(R.id.message_title);
            message_desc = itemView.findViewById(R.id.message_desc);
            message_date = itemView.findViewById(R.id.message_date);
            message_tag = itemView.findViewById(R.id.isTag);
            card_background = itemView.findViewById(R.id.card_bg);
            timelineView = itemView.findViewById(R.id.time_marker);

        }
    }
    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        // 设置标题
        holder.message_title.setText(messagesList.get(messagesList.size()-1-position).getTitle());
        // 详情
        holder.message_desc.setText(messagesList.get(messagesList.size() - 1 - position).getDesc());
        // 设置时间
        holder.message_date.setText(messagesList.get(messagesList.size() - 1 - position).getDate()+ " " + messagesList.get(messagesList.size() - 1 - position).getTime());
        // 设置签到状态
        holder.message_tag.setText(messagesList.get(messagesList.size()-1-position).getIsSignTag());
        // 设置背景图
        holder.card_background.setImageBitmap(BitmapUtil.readBitMap(context, messagesList.get(messagesList.size() - 1 - position).getImgId()));
    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }

    // 左滑删除弹框提示
    private void popAlertDialog() {

        if (dialog == null) {
            dialog = new MaterialDialog(context);
            dialog.setMessage("确定删除？")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Messages messages = messagesList.get(truePosition);
//                            dbHelper = new MyDatabaseHelper(context, "Data.db", null, 2);
//                            SQLiteDatabase db = dbHelper.getWritableDatabase();
//                            db.delete("Todo", "todotitle = ?",
//                                    new String[]{todosList.get(truePosition).getTitle()});
////                                new ToDoDao(getContext()).deleteTask(todosList);
////                                todosList.delete(new UpdateListener() {
////                                    @Override
////                                    public void done(BmobException e) {
////                                        if (e==null){
////                                            todoRecyclerViewAdapter.removeItem(position);
////                                        } else {
////                                            ToastUtils.showShort(getContext(),e.getMessage());
////                                        }
////                                    }
////                                });
//
//                            if (User.getCurrentUser(User.class) != null) {
//                                ToDoUtils.deleteNetTodos(context, todos, new ToDoUtils.DeleteTaskListener() {
//                                    @Override
//                                    public void onSuccess() {
//
//                                    }
//
//                                    @Override
//                                    public void onError(int errorCord, String msg) {
//                                        Toasty.warning(context, msg, Toast.LENGTH_SHORT, true).show();
//                                    }
//                                });
//                            }
                            messagesList.remove(truePosition);
                            notifyItemRemoved(itemPosition);
                            notifyItemRangeChanged(itemPosition, messagesList.size());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("取消", new View.OnClickListener() {
                        public void onClick(View view) {
                            notifyItemChanged(itemPosition);
                            dialog.dismiss();
                        }
                    });
        }
        dialog.show();
    }
}
