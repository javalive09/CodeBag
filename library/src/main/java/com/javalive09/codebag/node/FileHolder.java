package com.javalive09.codebag.node;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.javalive09.codebag.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 *
 * Created by peter
 *
 */
public class FileHolder extends TreeNode.BaseNodeViewHolder<NodeItem> {

    private TextView tvValue;
    private PrintView arrowView;

    public FileHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, NodeItem value) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_node, null, false);

        String text = value.text;
        if(value.icon == NodeItem.CLASS) {
            text = text + ".java";
        }else if(value.icon == NodeItem.METHOD) {
            text = text + "( )";
        }

        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(text);

        final PrintView iconView = (PrintView) view.findViewById(R.id.icon);
        iconView.setIconText(context.getResources().getString(value.icon));

        arrowView = (PrintView) view.findViewById(R.id.arrow_icon);
        if (node.isLeaf()) {
            arrowView.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }


}
