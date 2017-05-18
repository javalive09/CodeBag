
 
package com.javalive09.sample.project.xui.mapdetail;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

/**
 * This class defines an ExpandableListView which supports animations for
 * collapsing and expanding groups.
 */
public class AnimatedExpandableListView extends ExpandableListView {

    @SuppressWarnings("unused")
	private static final String TAG = AnimatedExpandableListAdapter.class.getSimpleName();

    /**
     * The duration of the expand/collapse animations
     */
    private static final int ANIMATION_DURATION = 500;

    private AnimatedExpandableListAdapter adapter;

    public AnimatedExpandableListView(Context context) {
        super(context);
    }

    public AnimatedExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedExpandableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @see ExpandableListView#setAdapter(ExpandableListAdapter)
     */
    public void setAdapter(ExpandableListAdapter adapter) {
        super.setAdapter(adapter);

        // Make sure that the adapter extends AnimatedExpandableListAdapter
        if(adapter instanceof AnimatedExpandableListAdapter) {
            this.adapter = (AnimatedExpandableListAdapter) adapter;
            this.adapter.setParent(this);
        } else {
            throw new ClassCastException(adapter.toString() + " must implement AnimatedExpandableListAdapter");
        }
    }

    /**
     * Expands the given group with an animation.
     * @param groupPos The position of the group to expand
     * @return  Returns true if the group was expanded. False if the group was
     *          already expanded.
     */
    @SuppressLint("NewApi") 
    public boolean expandGroupWithAnimation(int groupPos) {
    	boolean lastGroup = groupPos == adapter.getGroupCount() - 1;
    	if (lastGroup && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
    		return expandGroup(groupPos, true);
    	}
    	
        int groupFlatPos = getFlatListPosition(getPackedPositionForGroup(groupPos));
        if (groupFlatPos != -1) {
            int childIndex = groupFlatPos - getFirstVisiblePosition();
            if (childIndex < getChildCount()) {
                // Get the view for the group is it is on screen...
                View v = getChildAt(childIndex);
                if (v.getBottom() >= getBottom()) {
                    // If the user is not going to be able to see the animation
                    // we just expand the group without an animation.
                    // This resolves the case where getChildView will not be
                    // called if the children of the group is not on screen

                    // We need to notify the adapter that the group was expanded
                    // without it's knowledge
                    adapter.notifyGroupExpanded(groupPos);
                    return expandGroup(groupPos);
                }
            }
        }

        // Let the adapter know that we are starting the animation...
        adapter.startExpandAnimation(groupPos, 0);
        // Finally call expandGroup (note that expandGroup will call
        // notifyDataSetChanged so we don't need to)
        return expandGroup(groupPos);
    }

    /**
     * Collapses the given group with an animation.
     * @param groupPos The position of the group to collapse
     * @return  Returns true if the group was collapsed. False if the group was
     *          already collapsed.
     */
    public boolean collapseGroupWithAnimation(int groupPos) {
        int groupFlatPos = getFlatListPosition(getPackedPositionForGroup(groupPos));
        if (groupFlatPos != -1) {
            int childIndex = groupFlatPos - getFirstVisiblePosition();
            if (childIndex >= 0 && childIndex < getChildCount()) {
                // Get the view for the group is it is on screen...
                View v = getChildAt(childIndex);
                if (v.getBottom() >= getBottom()) {
                    // If the user is not going to be able to see the animation
                    // we just collapse the group without an animation.
                    // This resolves the case where getChildView will not be
                    // called if the children of the group is not on screen
                    return collapseGroup(groupPos);
                }
            } else {
                // If the group is offscreen, we can just collapse it without an
                // animation...
                return collapseGroup(groupPos);
            }
        }

        // Get the position of the firstChild visible from the top of the screen
        long packedPos = getExpandableListPosition(getFirstVisiblePosition());
        int firstChildPos = getPackedPositionChild(packedPos);
        int firstGroupPos = getPackedPositionGroup(packedPos);

        // If the first visible view on the screen is a child view AND it's a
        // child of the group we are trying to collapse, then set that
        // as the first child position of the group... see
        // {@link #startCollapseAnimation(int, int)} for why this is necessary
        firstChildPos = firstChildPos == -1 || firstGroupPos != groupPos ? 0 : firstChildPos;

        // Let the adapter know that we are going to start animating the
        // collapse animation.
        adapter.startCollapseAnimation(groupPos, firstChildPos);

        // Force the listview to refresh it's views
        adapter.notifyDataSetChanged();
        return isGroupExpanded(groupPos);
    }

    private int getAnimationDuration() {
        return ANIMATION_DURATION;
    }

    /**
     * Used for holding information regarding the group.
     */
    private static class GroupInfo {
        boolean animating = false;
        boolean expanding = false;
        int firstChildPosition;

        /**
         * This variable contains the last known height value of the dummy view.
         * We save this information so that if the user collapses a group
         * before it fully expands, the collapse animation will start from the
         * CURRENT height of the dummy view and not from the full expanded
         * height.
         */
        int dummyHeight = -1;
    }

    /**
     * A specialized adapter for use with the AnimatedExpandableListView. All
     * adapters used with AnimatedExpandableListView MUST extend this class.
     */
    public static abstract class AnimatedExpandableListAdapter extends BaseExpandableListAdapter {
        private SparseArray<GroupInfo> groupInfo = new SparseArray<GroupInfo>();
        private AnimatedExpandableListView parent;

        private static final int STATE_IDLE = 0;
        private static final int STATE_EXPANDING = 1;
        private static final int STATE_COLLAPSING = 2;

        private void setParent(AnimatedExpandableListView parent) {
            this.parent = parent;
        }

        public int getRealChildType(int groupPosition, int childPosition) {
            return 0;
        }

        public int getRealChildTypeCount() {
            return 1;
        }

        public abstract View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent);
        public abstract int getRealChildrenCount(int groupPosition);

        private GroupInfo getGroupInfo(int groupPosition) {
            GroupInfo info = groupInfo.get(groupPosition);
            if (info == null) {
                info = new GroupInfo();
                groupInfo.put(groupPosition, info);
            }
            return info;
        }

        public void notifyGroupExpanded(int groupPosition) {
            GroupInfo info = getGroupInfo(groupPosition);
            info.dummyHeight = -1;
        }

        private void startExpandAnimation(int groupPosition, int firstChildPosition) {
            GroupInfo info = getGroupInfo(groupPosition);
            info.animating = true;
            info.firstChildPosition = firstChildPosition;
            info.expanding = true;
        }

        private void startCollapseAnimation(int groupPosition, int firstChildPosition) {
            GroupInfo info = getGroupInfo(groupPosition);
            info.animating = true;
            info.firstChildPosition = firstChildPosition;
            info.expanding = false;
        }

        private void stopAnimation(int groupPosition) {
            GroupInfo info = getGroupInfo(groupPosition);
            info.animating = false;
        }

        /**
         * Override {@link #getRealChildType(int, int)} instead.
         */
        @Override
        public final int getChildType(int groupPosition, int childPosition) {
            GroupInfo info = getGroupInfo(groupPosition);
            if (info.animating) {
                // If we are animating this group, then all of it's children
                // are going to be dummy views which we will say is type 0.
                return 0;
            } else {
                // If we are not animating this group, then we will add 1 to
                // the type it has so that no type id conflicts will occur
                // unless getRealChildType() returns MAX_INT
                return getRealChildType(groupPosition, childPosition) + 1;
            }
        }

        /**
         * Override {@link #getRealChildTypeCount()} instead.
         */
        @Override
        public final int getChildTypeCount() {
            // Return 1 more than the childTypeCount to account for DummyView
            return getRealChildTypeCount() + 1;
        }
        
        protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
            return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        }

        /**
         * Override {@link #getChildView(int, int, boolean, View, ViewGroup)} instead.
         */
        @Override
        public final View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
            final GroupInfo info = getGroupInfo(groupPosition);

            if (info.animating) {
                // If this group is animating, return the a DummyView...
                if (convertView instanceof DummyView == false) {
                    convertView = new DummyView(parent.getContext());
                    convertView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0));
                }

                if (childPosition < info.firstChildPosition) {
                    // The reason why we do this is to support the collapse
                    // this group when the group view is not visible but the
                    // children of this group are. When notifyDataSetChanged
                    // is called, the ExpandableListView tries to keep the
                    // list position the same by saving the first visible item
                    // and jumping back to that item after the views have been
                    // refreshed. Now the problem is, if a group has 2 items
                    // and the first visible item is the 2nd child of the group
                    // and this group is collapsed, then the dummy view will be
                    // used for the group. But now the group only has 1 item
                    // which is the dummy view, thus when the ListView is trying
                    // to restore the scroll position, it will try to jump to
                    // the second item of the group. But this group no longer
                    // has a second item, so it is forced to jump to the next
                    // group. This will cause a very ugly visual glitch. So
                    // the way that we counteract this is by creating as many
                    // dummy views as we need to maintain the scroll position
                    // of the ListView after notifyDataSetChanged has been
                    // called.
                    convertView.getLayoutParams().height = 0;
                    return convertView;
                }

                final ExpandableListView listView = (ExpandableListView) parent;

                final DummyView dummyView = (DummyView) convertView;

                // Clear the views that the dummy view draws.
                dummyView.clearViews();

                // Set the style of the divider
//                dummyView.setDivider(listView.getDivider(), parent.getMeasuredWidth(), listView.getDividerHeight());

                // Make measure specs to measure child views
                final int measureSpecW = MeasureSpec.makeMeasureSpec(parent.getWidth(), MeasureSpec.EXACTLY);
                final int measureSpecH = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

                int totalHeight = 0;
                int clipHeight = parent.getHeight();

                final int len = getRealChildrenCount(groupPosition);
                for (int i = info.firstChildPosition; i < len; i++) {
                    View childView = getRealChildView(groupPosition, i, (i == len - 1), null, parent);
                    
                    LayoutParams p = (LayoutParams) childView.getLayoutParams();
                    if (p == null) {
                        p = (LayoutParams) generateDefaultLayoutParams();
                        childView.setLayoutParams(p);
                    }
                    
                    int lpHeight = p.height;
                    
                    int childHeightSpec;
                    if (lpHeight > 0) {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
                    } else {
                        childHeightSpec = measureSpecH;
                    }
                    
                    childView.measure(measureSpecW, childHeightSpec);
                    totalHeight += childView.getMeasuredHeight();
                    
                    if (totalHeight < clipHeight) {
                        // we only need to draw enough views to fool the user...
                        dummyView.addFakeView(childView);
                    } else {
                        dummyView.addFakeView(childView);
                        
                        // if this group has too many views, we don't want to
                        // calculate the height of everything... just do a light
                        // approximation and break
                        int averageHeight = totalHeight / (i + 1);
                        totalHeight += (len - i - 1) * averageHeight;
                        break;
                    }
                }

                Object o;
                int state = (o = dummyView.getTag()) == null ? STATE_IDLE : (Integer) o;

                if (info.expanding && state != STATE_EXPANDING) {
                    ExpandAnimation ani = new ExpandAnimation(dummyView, 0, totalHeight, info);
                    ani.setDuration(this.parent.getAnimationDuration());
                    ani.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            stopAnimation(groupPosition);
                            notifyDataSetChanged();
                            dummyView.setTag(STATE_IDLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {}

                        @Override
                        public void onAnimationStart(Animation animation) {}

                    });
                    dummyView.startAnimation(ani);
                    dummyView.setTag(STATE_EXPANDING);
                } else if (!info.expanding && state != STATE_COLLAPSING) {
                    if (info.dummyHeight == -1) {
                        info.dummyHeight = totalHeight;
                    }

                    ExpandAnimation ani = new ExpandAnimation(dummyView, info.dummyHeight, 0, info);
                    ani.setDuration(this.parent.getAnimationDuration());
                    ani.setAnimationListener(new AnimationListener() {

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            stopAnimation(groupPosition);
                            listView.collapseGroup(groupPosition);
                            notifyDataSetChanged();
                            info.dummyHeight = -1;
                            dummyView.setTag(STATE_IDLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {}

                        @Override
                        public void onAnimationStart(Animation animation) {}

                    });
                    dummyView.startAnimation(ani);
                    dummyView.setTag(STATE_COLLAPSING);
                }

                return convertView;
            } else {
                return getRealChildView(groupPosition, childPosition, isLastChild, convertView, parent);
            }
        }

        @Override
        public final int getChildrenCount(int groupPosition) {
            GroupInfo info = getGroupInfo(groupPosition);
            if (info.animating) {
                return info.firstChildPosition + 1;
            } else {
                return getRealChildrenCount(groupPosition);
            }
        }

    }

    private static class DummyView extends View {
        private List<View> views = new ArrayList<View>();
        private Drawable divider;
        private int dividerWidth;
        private int dividerHeight;

        public DummyView(Context context) {
            super(context);
        }

        public void setDivider(Drawable divider, int dividerWidth, int dividerHeight) {
	        if(divider != null) {
		        this.divider = divider;
		        this.dividerWidth = dividerWidth;
		        this.dividerHeight = dividerHeight;

		        divider.setBounds(0, 0, dividerWidth, dividerHeight);
	        }
        }

        /**
         * Add a view for the DummyView to draw.
         * @param childView View to draw
         */
        public void addFakeView(View childView) {
            childView.layout(0, 0, getWidth(), childView.getMeasuredHeight());
            views.add(childView);
        }
        
        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            final int len = views.size();
            for(int i = 0; i < len; i++) {
                View v = views.get(i);
                v.layout(left, top, left + v.getMeasuredWidth(), top + v.getMeasuredHeight());
            }
        }

        public void clearViews() {
            views.clear();
        }

        @Override
        public void dispatchDraw(Canvas canvas) {
            canvas.save();
            if(divider != null) {
                divider.setBounds(0, 0, dividerWidth, dividerHeight);
            }
            
            final int len = views.size();
            for(int i = 0; i < len; i++) {
                View v = views.get(i);
                
                canvas.save();
                canvas.clipRect(0, 0, getWidth(), v.getMeasuredHeight());
                v.draw(canvas);
                canvas.restore();
                
                if(divider != null) {
                    divider.draw(canvas);
                    canvas.translate(0, dividerHeight);
                }
                
                canvas.translate(0, v.getMeasuredHeight());
            }
            
            canvas.restore();
        }
    }

    private static class ExpandAnimation extends Animation {
        private int baseHeight;
        private int delta;
        private View view;
        private GroupInfo groupInfo;

        private ExpandAnimation(View v, int startHeight, int endHeight, GroupInfo info) {
            baseHeight = startHeight;
            delta = endHeight - startHeight;
            view = v;
            groupInfo = info;

            view.getLayoutParams().height = startHeight;
            view.requestLayout();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                int val = baseHeight + (int) (delta * interpolatedTime);
                view.getLayoutParams().height = val;
                groupInfo.dummyHeight = val;
                view.requestLayout();
            } else {
                int val = baseHeight + delta;
                view.getLayoutParams().height = val;
                groupInfo.dummyHeight = val;
                view.requestLayout();
            }
        }
    }


}
