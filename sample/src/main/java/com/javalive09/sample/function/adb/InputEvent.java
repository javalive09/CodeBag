package com.javalive09.sample.function.adb;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.utils.ShellUtil;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/6/5.
 */

public class InputEvent extends Entry {

    public void home() {
        key(3);
    }

    public void back() {
        key(4);
    }

    public void volumeUp() {
        key(24);
    }

    public void volumeDown() {
        key(25);
    }

    public void menu() {
        key(82);
    }

    public void call() {
        key(5);
    }

    public void camera() {
        key(27);
    }

    public void power() {
        key(26);
    }

    public void mute() {
        key(91);
    }

    public void swip() {
        swip(600, 600, 1000,600);
    }

    public void tap() {
        tap(600, 600);
    }

    public void roll() {
        roll(0, 900);
    }

    private void key(int key) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("keyevent");
            commands.add(key + "");
            ShellUtil.cmd(commands);
        });
    }

    private void swip(int x1, int y1, int x2, int y2) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("swipe");
            commands.add(x1 + "");
            commands.add(y1 + "");
            commands.add(x2 + "");
            commands.add(y2 + "");
            ShellUtil.cmd(commands);
        });
    }

    private void tap(int x1, int y1) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("tap");
            commands.add(x1 + "");
            commands.add(y1 + "");
            ShellUtil.cmd(commands);
        });
    }

    private void roll(int x1, int y1) {
        Observable.just(1).observeOn(Schedulers.io()).subscribe(integer -> {
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("input");
            commands.add("roll");
            commands.add(x1 + "");
            commands.add(y1 + "");
            ShellUtil.cmd(commands);
        });
    }

}
