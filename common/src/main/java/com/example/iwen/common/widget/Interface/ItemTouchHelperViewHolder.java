package com.example.iwen.common.widget.Interface;

import androidx.recyclerview.widget.ItemTouchHelper;

/**
 * @author iwen大大怪
 * Create to 2020/4/7 10:29
 */
///**
// * Interface to notify an item ViewHolder of relevant callbacks from {@link
// * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
// *
// * @author Paul Burke (ipaulpro)
// */
public interface ItemTouchHelperViewHolder {
    /**
     * Called when the {@link ItemTouchHelper} first registers an item as being moved or swiped.
     * Implementations should update the item view to indicate it's active state.
     */
    void onItemSelected();


    /**
     * Called when the {@link ItemTouchHelper} has completed the move or swipe, and the active item
     * state should be cleared.
     */
    void onItemClear();
}
