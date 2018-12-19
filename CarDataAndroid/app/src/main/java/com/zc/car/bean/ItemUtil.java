package com.zc.car.bean;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;


public class ItemUtil {

    private ItemUtil() {
    }


    public static boolean isLastColumn(RecyclerView parent, int position, int childCount, View view) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int orientation = manager.getOrientation();
            int spanCount = manager.getSpanCount();
            if (orientation == LinearLayoutManager.VERTICAL) {
                // 判断是否是最后一列
                if ((position + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                int remainder = childCount % spanCount;
                childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
                // 判断方向横是否是最后一列
                if (position >= childCount) {
                    return true;
                }
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            int orientation = manager.getOrientation();
            int spanCount = manager.getSpanCount();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // 判断方向竖是否是最后一列
                final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                return slp.getSpanIndex() == spanCount - 1;
            } else {
                int remainder = childCount % spanCount;
                childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
                // 判断方向横是否是最后一列
                if (position >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isLastColumn(int position, int childCount, int spanCount, boolean isVertical, boolean isGrid, View view) {

        if (isGrid) {
            if (isVertical) {
                // 判断是否是最后一列
                if ((position + 1) % spanCount == 0) {
                    return true;
                }
            } else {
                int remainder = childCount % spanCount;
                childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
                // 判断方向横是否是最后一列
                if (position >= childCount) {
                    return true;
                }
            }
        } else {
            if (isVertical) {
                final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                return slp.getSpanIndex() == spanCount - 1;
            } else {
                int remainder = childCount % spanCount;
                childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
                // 判断方向横是否是最后一列
                if (position >= childCount) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isLastRaw(int position, int childCount, int spanCount, boolean isVertical, boolean isGrid, View view) {
        if (isGrid) {
            if (isVertical) {
                int remainder = childCount % spanCount;
                childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
                // 判断方向竖是否是最后一行
                if (position >= childCount) {
                    return true;
                }
            } else {
                // 判断方向横是否是最后一行
                if ((position + 1) % spanCount == 0) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean isLastRaw(RecyclerView parent, int position, int childCount, View view) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            int orientation = manager.getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                int remainder = childCount % spanCount;
                childCount = childCount - remainder - (remainder == 0 ? spanCount : 0);
                // 判断方向竖是否是最后一行
                if (position >= childCount) {
                    return true;
                }
            } else {
                // 判断方向横是否是最后一行
                if ((position + 1) % spanCount == 0) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean isFirstRaw(RecyclerView parent, int position, View view) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int spanCount = manager.getSpanCount();
            int orientation = manager.getOrientation();
            if (orientation == LinearLayoutManager.VERTICAL) {
                return position / spanCount == 0;
            } else {
                return position % spanCount == 0;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            int orientation = manager.getOrientation();
            int spanCount = manager.getSpanCount();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                return position / spanCount == 0;
            } else {
                final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                return slp.getSpanIndex() == 0;
            }
        }
        return false;
    }


    public static boolean isFirstColumn(RecyclerView parent, int position, View view) {

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager manager = (GridLayoutManager) layoutManager;
            int orientation = manager.getOrientation();
            int spanCount = manager.getSpanCount();
            if (orientation == LinearLayoutManager.VERTICAL) {
                return position % spanCount == 0;
            } else {
                return position / spanCount == 0;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
            int orientation = manager.getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                final StaggeredGridLayoutManager.LayoutParams slp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                return slp.getSpanIndex() == 0;
            } else {
                int spanCount = manager.getSpanCount();
                return position / spanCount == 0;
            }
        }
        return false;
    }
}
