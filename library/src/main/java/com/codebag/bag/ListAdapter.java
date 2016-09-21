package com.codebag.bag;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    static final int NO_ENTRY = 0; // 不含有入口方法
    static final int ENTRY = 1; // 含有入口方法

    CodeBagActivity mActivity;
    LayoutInflater factory;
    List<Node> mList;

    public ListAdapter(CodeBagActivity context, ArrayList<Node> list) {
        factory = LayoutInflater.from(context);
        mActivity = context;
        mList = list;
    }

    @Override
    public int getCount() {
        if(mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Node getItem(int position) {
        if(mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = factory.inflate(R.layout.main_list_item, parent, false);
            holder = new Holder();
            holder.text = (TextView) convertView.findViewById(R.id.list_item_text);
            holder.icon = (ImageView) convertView.findViewById(R.id.list_item_icon);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Node node = getItem(position);
        switch (node.type) {
            case Node.CLASS:
                holder.icon.setImageResource(R.drawable.file);
                String txt = String.format(mActivity.getString(R.string.item_string_java), node.name);
                holder.text.setText(txt);
                break;
            case Node.DIR:
                holder.icon.setImageResource(R.drawable.folder);
                holder.text.setText(node.name);
                break;
            case Node.METHOD:
                holder.icon.setImageResource(R.drawable.run);
                txt = String.format(mActivity.getString(R.string.item_string_method), node.name);
                holder.text.setText(txt);
                break;
            default:
                break;
        }

        convertView.setEnabled(getItemViewType(position) == ENTRY);
        convertView.setOnClickListener(mActivity);
        convertView.setOnLongClickListener(mActivity);
        convertView.setTag(R.id.main_item_pos, position);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        Node node = getItem(position);
        if (node.type == Node.CLASS) {
            String className = node.className;
            try {
                Class<?> cls = Class.forName(className);
                String superClassName = cls.getSuperclass().getSimpleName();
                if (superClassName.equals("Entry")) {
                    Method[] methods = cls.getDeclaredMethods();
                    for (Method m : methods) {
                        if (Modifier.PUBLIC == m.getModifiers()) {
                            return ENTRY;
                        }
                    }
                }
                return NO_ENTRY;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ENTRY;
    }

    class Holder {
        ImageView icon;
        TextView text;
    }
}