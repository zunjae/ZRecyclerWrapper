package com.zunjae.zrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * Created by zunjae on 6/4/2017.
 */

public final class ZRecyclerView {

    private static final String TAG = "Builder";

    @NonNull
    private final Context context;

    @NonNull
    private final RecyclerView recyclerView;

    @NonNull
    private final RecyclerView.Adapter adapter;

    /**
     * The column count when in portrait
     */
    private int columnSizePortrait = 1;

    /**
     * The column count when in landscape
     */
    private int columnSizeLandscape = -1;

    /**
     * The user selected LayoutManager type, by default Linear until specified by user
     */
    private LayoutManagerType layoutManagerType = LayoutManagerType.LINEAR;

    private RecyclerView.LayoutManager layoutManager;

    public ZRecyclerView(@NonNull Context context,
                         @NonNull RecyclerView recyclerView,
                         @NonNull RecyclerView.Adapter adapter) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }

    public ZRecyclerView withColumnSizePortrait(int columnSizePortrait) {
        this.columnSizePortrait = columnSizePortrait;
        return this;
    }

    public ZRecyclerView withColumnSizeLandscape(int columnSizeLandscape) {
        this.columnSizeLandscape = columnSizeLandscape;
        return this;
    }

    public ZRecyclerView withColumnSizes(int portrait, int landscape) {
        this.columnSizeLandscape = landscape;
        this.columnSizePortrait = portrait;
        return this;
    }

    public ZRecyclerView withColumnSizes(int columnSize) {
        this.columnSizeLandscape = columnSize;
        this.columnSizePortrait = columnSize;
        return this;
    }

    public ZRecyclerView withLayoutManager(LayoutManagerType layoutManagerType) {
        this.layoutManagerType = layoutManagerType;
        return this;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public ZRecyclerView build() {
        if (layoutManagerType != LayoutManagerType.LINEAR && columnSizeLandscape < 0) {
            Log.i(TAG, "[build] No landscape column count set, assuming portrait");
            columnSizeLandscape = columnSizePortrait;
        }

        int spanCount;
        boolean isPortrait = OrientationUtil.phoneIsInPortrait(context);
        if (isPortrait) {
            spanCount = columnSizePortrait;
        } else {
            spanCount = columnSizeLandscape;
        }

        switch (layoutManagerType) {
            case LINEAR:
                layoutManager = new LinearLayoutManager(context);
                break;
            case GRID:
                layoutManager = new GridLayoutManager(context, spanCount);
                break;
            case STAGGERED:
                // todo : check how this works
                layoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.HORIZONTAL);
                break;
            default:
                throw new IllegalArgumentException("incorrect enum set");
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return this;
    }
}
