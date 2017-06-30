package hu.wup.ferencnagy.populartimes.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Class for implementing custom item click handling in a recycler view.
 * @author ferencnagy
 */
public class RecyclerViewOnItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener onItemClickListener;
    private GestureDetector gestureDetector;

    /**
     * Interface for listening item click events.
     */
    public interface OnItemClickListener {

        /**
         * Method to execute in case of item clicking.
         * @param view Item view.
         * @param position Current position of the item view.
         */
        void onItemClick(View view, int position);
    }

    /**
     * Constructor.
     * @param context Context.
     * @param listener Item click listener.
     */
    public RecyclerViewOnItemClickListener(Context context, OnItemClickListener listener) {
        onItemClickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    /**
     * Overridden method of onInterceptTouchEvent for the sake of handling gesture detection
     * of a given recycler view item and fire the event of item clicking.
     * @param recyclerView Recycler view.
     * @param motionEvent Motion event.
     * @return Fact to continue observing future events in the gesture.
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (childView != null && onItemClickListener != null && gestureDetector.onTouchEvent(motionEvent)) {
            onItemClickListener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView));
        }
        return false;
    }

    /**
     * Overridden method of onTouchEvent.
     * @param recyclerView Recycler view.
     * @param motionEvent Motion event.
     */
    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }

    /**
     * Overridden method of onRequestDisallowInterceptTouchEvent.
     * @param disallowIntercept Interception disallowment fact.
     */
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
