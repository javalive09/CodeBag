package com.codebag.bag;


import com.codebag.bag.CodeBag.Node;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

public class CaseListView extends ListView {

	public static final String METHOD_PREFIX = "run_";
	
	public CaseListView(Context context) {
		super(context);
	}
	
	public void showView(View view) {
		StackTraceElement[] e = Thread.currentThread().getStackTrace();
		Intent intent = new Intent(getContext(), MainActivity.class);
		Node node = new Node();
		node.mName = e[3].getMethodName();
		node.mType = Node.METHOD;
		MainActivity act = (MainActivity) getContext();
		CodeBag codeBag = (CodeBag) act.getApplication();
		codeBag.setCurrentMethodView(view);
		codeBag.setCurrentNode(node);
//		intent.putExtra(MainActivity.NODE_NAME, node);
		act.startActivity(intent);
	}

}
