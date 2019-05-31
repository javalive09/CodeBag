package com.javalive09.demos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.text.TextUtils;

/**
 * Created by peter on 2019-05-29
 */
public class LeetCode {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int length = s.length();
        List<Character> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.clear();
            list.add(s.charAt(i));
            for (int j = i + 1; j < length; j++) {
                Character character = s.charAt(j);
                if (list.contains(character)) {
                    break;
                } else {
                    list.add(character);
                }
            }
            maxLen = Math.max(maxLen, list.size());
        }
        return maxLen;
    }

    @Run
    public void lengthOfLongestSubstring(CodeActivity codeActivity) {
        codeActivity.showText(lengthOfLongestSubstring("babcebcbb") + "");
    }

    // 1211
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else {
            String s = countAndSay(n - 1);
            int len = s.length();
            ArrayList<Character> list = new ArrayList<>(len);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                Character character = s.charAt(i);
                if (list.size() == 0) {
                    list.add(character);
                } else {
                    if (list.contains(character)) {
                        list.add(character);
                    } else {
                        stringBuilder.append(list.size()).append(list.get(0));
                        list.clear();
                        list.add(character);
                    }
                }
                if (i == len - 1) {
                    stringBuilder.append(list.size()).append(list.get(0));
                }
            }
            return stringBuilder.toString();
        }
    }

    @Run
    public void countAndSay(CodeActivity codeActivity) {
        codeActivity.showText(countAndSay(3));
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Run
    public void mergeTwoLists(CodeActivity codeActivity) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);

        mergeTwoLists(l1, l2);
        codeActivity.showText("ddd");
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ArrayList<Integer> list = new ArrayList<>();
        addAll(l1, list);
        addAll(l2, list);
        Collections.sort(list);
        ListNode listNode = null;
        ListNode currentNode = null;
        for (int i = 0; i < list.size(); i++) {
            if (listNode == null) {
                listNode = new ListNode(list.get(i));
                currentNode = listNode;
            } else {
                currentNode.next = new ListNode(list.get(i));
                currentNode = currentNode.next;
            }
        }
        return listNode;
    }

    private void addAll(ListNode listNode, ArrayList<Integer> list) {
        if (listNode != null) {
            list.add(listNode.val);
            if (listNode.next != null) {
                addAll(listNode.next, list);
            }
        }
    }

    public int searchInsert(int[] nums, int target) {

        int pos = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == target) {
                return i;
            }
            if (nums[i] > target) {
                pos = i;
            }
        }

        if (pos == -1) {
            pos = len + 1;
        }

        return pos;
    }

    @Run
    public void searchInsert(CodeActivity codeActivity) {
        codeActivity.showText(searchInsert(new int[]{1,3,5,6}, 2) + "");
    }

}
