package com.codebag.code.mycode.view.person108;

import android.widget.FrameLayout;
import android.widget.ImageView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Card extends MyCode {

    public Card(InovkedViewActivity act) {
        super(act);
    }
    
    @Entry
    public void show() {
        ImageView card = new ImageView(getActivity());
        card.setImageResource(R.drawable.hubaoyi);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        showView(card, params);
    }

}
