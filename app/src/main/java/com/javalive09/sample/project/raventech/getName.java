package com.javalive09.sample.project.raventech;

import com.javalive09.codebag.Entry;

/**
 * Created by peter on 2016/12/12.
 */

public class getName extends Entry {

    public void getName() {
        showTxt(getActivity().getPackageName() + "/" + getName.class.getName());
    }

    public void getSimpleName() {
        showTxt(getActivity().getPackageName() + "/" + getName.class.getSimpleName());
    }

}
