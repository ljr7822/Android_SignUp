package com.example.iwen.common.widget.Interface;

/**
 * @author iwen大大怪
 * Create to 2020/4/8 2:31
 */

import androidx.recyclerview.widget.RecyclerView;

/**
 * Listener for manual initiation of a drag.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
