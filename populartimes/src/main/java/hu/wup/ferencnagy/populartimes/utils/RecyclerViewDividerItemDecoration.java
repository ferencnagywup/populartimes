package hu.wup.ferencnagy.populartimes.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import hu.wup.ferencnagy.populartimes.R;

/**
 * Class for creating a custom separator line as recycler view divider item decoration.
 * @author ferencnagy
 */
public class RecyclerViewDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable separatorLine;

    /**
     * Constructor for getting the separator line drawable.
     * @param context Context.
     */
    public RecyclerViewDividerItemDecoration(Context context) {
        separatorLine = ContextCompat.getDrawable(context, R.drawable.recyclerview_separator_line);
    }

    /**
     * Overridden method of onDrawOver for the sake of rendering the separator line.
     * @param canvas Canvas object.
     * @param recyclerView Parent object (the recycler view).
     * @param state Current state of the recycler view.
     */
    @Override
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int leftPadding = recyclerView.getPaddingLeft();
        int rightPadding = recyclerView.getWidth() - recyclerView.getPaddingRight();

        int recyclerViewChildCount = recyclerView.getChildCount();
        for (int i = 0; i < recyclerViewChildCount; i++) {
            View childView = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int topPadding = childView.getBottom() + params.bottomMargin;
            int bottomPadding = topPadding + separatorLine.getIntrinsicHeight();

            separatorLine.setBounds(leftPadding, topPadding, rightPadding, bottomPadding);
            separatorLine.draw(canvas);
        }
    }
}
