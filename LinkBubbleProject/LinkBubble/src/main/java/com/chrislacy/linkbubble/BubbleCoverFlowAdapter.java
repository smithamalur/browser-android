package com.chrislacy.linkbubble;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

import java.net.URL;
import java.util.List;

class BubbleCoverFlowAdapter extends FancyCoverFlowAdapter {

    Context mContext;
    List<Bubble> mBubbles;
    int mItemSize;

    public final int mSize;
    private final int mMiddleIndex;

    public BubbleCoverFlowAdapter(Context context, List<Bubble> bubbles) {
        super();
        mContext = context;
        mBubbles = bubbles;

        mItemSize = context.getResources().getDimensionPixelSize(R.dimen.bubble_cover_flow_item_size);

        mSize = Integer.MAX_VALUE;
        int halfMaxValue = mSize/2;
        mMiddleIndex = halfMaxValue - halfMaxValue % bubbles.size();
    }

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    public int getMiddleIndex() {
        return mMiddleIndex;
    }

    @Override
    public int getCount() {
        return mSize;
    }

    @Override
    public Bubble getItem(int position) {
        return mBubbles.get(position % mBubbles.size());
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getCoverFlowItem(int i, View reuseableView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (reuseableView != null) {
            viewHolder = (ViewHolder) reuseableView.getTag();
        } else {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            reuseableView = inflater.inflate(R.layout.view_bubble_cover_flow_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.mFaviconImageView = (ImageView) reuseableView.findViewById(R.id.favicon);
            //viewHolder.mTextView = (TextView) reuseableView.findViewById(R.id.text_view);
            reuseableView.setTag(viewHolder);
            reuseableView.setLayoutParams(new FancyCoverFlow.LayoutParams(mItemSize, mItemSize));
        }

        Bubble info = getItem(i);
        //viewHolder.mTextView.setText(info.getUrl().getHost());
        viewHolder.mFaviconImageView.setImageDrawable(info.getFavicon());
        return reuseableView;
    }

    private static class ViewHolder {
        //TextView mTextView;
        ImageView mFaviconImageView;
    }
}