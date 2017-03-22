package com.javalive09.codebag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 *
 * Created by peter on 2017/3/22.
 *
 */

public class TreeFragment extends Fragment {

    private TreeNode treeNode;
    private AndroidTreeView tView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FrameLayout result = (FrameLayout) inflater.inflate(R.layout.fragment_treeview, container, false);
        treeNode = ((MainActivity) getActivity()).getCodeNode();
        tView = new AndroidTreeView(getActivity(), treeNode);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setUse2dScroll(false);
        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }
        result.addView(tView.getView());
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}
