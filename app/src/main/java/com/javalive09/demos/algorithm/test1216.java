package com.javalive09.demos.algorithm;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class test1216 {

    public class Stack<T> {
        private Element<T> head;

        public T push(T t) {
            Element<T> e = new Element<>(t);
            if (head == null) {
                head = e;
            } else {
                e.next = head;
                head = e;
            }
            return t;
        }

        public T pop() {
            Element<T> h = head;
            head = head.next;
            return h.t;
        }

        public boolean empty() {
            return head == null;
        }

    }

    public class Element<T> {
        T t;
        Element<T> next;

        Element(T t) {
            this.t = t;
        }
    }


    @Run
    public void show(CodeActivity codeActivity) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        int i = stack.pop();
        int j = stack.pop();
        int k = stack.pop();
        codeActivity.showText("" + i + "" + j +  "" + k);
    }

}
