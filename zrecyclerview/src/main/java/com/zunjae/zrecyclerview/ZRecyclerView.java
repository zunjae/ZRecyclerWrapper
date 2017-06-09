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

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int columnSizePortrait = 1;
    private int columnSizeLandscape = -1;
    private LayoutManagerType layoutManagerType = LayoutManagerType.LINEAR;

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

    public void build() {
        if (layoutManagerType != LayoutManagerType.LINEAR && columnSizeLandscape < 0) {
            Log.i(TAG, "[build] No landscape column count set, assuming portrait");
            columnSizeLandscape = columnSizePortrait;
        }

        RecyclerView.LayoutManager layoutManager;
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
    }
}
