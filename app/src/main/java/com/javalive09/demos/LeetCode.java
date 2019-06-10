package com.javalive09.demos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

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

    // [1,3,5,6]
    public int searchInsert(int[] nums, int target) {

        int pos = -1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int value = nums[i];
            if (value == target) {
                return i;
            }

            if (target < value) {
                if (i > 0) {
                    if (target > nums[i - 1]) {
                        pos = i;
                    }
                } else {
                    pos = 0;
                }
            }

        }

        if (pos == -1) {
            pos = len;
        }

        return pos;
    }

    @Run
    public void searchInsert(CodeActivity codeActivity) {
        codeActivity.showText(searchInsert(new int[] {1, 3, 5, 6}, 2) + "");
    }

    // [0,0,1,1,1,2,2,3,3,4]
    public int removeDuplicates(int[] nums) {

        TreeSet<Integer> set = new TreeSet<>();

        //
        //        int pos = 0;
        //        List list = new ArrayList(nums.length);
        //        for(int i= 0; i< nums.length; i++) {
        //            Integer.
        //        }
        Integer[] integers = set.toArray(new Integer[set.size()]);
        for (int i = 0; i < integers.length; i++) {
            nums[i] = integers[i];
        }
        return integers.length;
    }

    @Run
    public void removeDuplicates(CodeActivity activity) {
        activity.showText(removeDuplicates(new int[] {1, 1, 2}) + "");
    }

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                boolean move = false;
                for (int j = i; j < len - 1; j++) {
                    nums[j] = nums[j + 1];
                    move = true;
                }
                if (move) {
                    len--;
                    i--;
                }
                if (i == len - 1) {
                    len--;
                }

            }
        }
        return len;
    }

    @Run
    public void removeElement(CodeActivity codeActivity) {
        codeActivity.showText(removeElement(new int[] {3, 2, 2, 3}, 3) + "");
    }

    public boolean isValid(String s) {
        Map<Character, Character> mappings = new HashMap<Character, Character>() {{
            put(']', '[');
            put(')', '(');
            put('}', '{');
        }};

        Stack<Character> stack = new Stack<>();

        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();

    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int reverse(int x) {
        StringBuilder stringBuilder = new StringBuilder().append((x > 0 ? x : -x)).reverse();
        int result = 0;
        try {
            result = (x > 0 ? 1 : -1) * Integer.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Run
    public void reverse(CodeActivity codeActivity) {
        codeActivity.showText(reverse(1534236469) + "");
    }

    public boolean isPalindrome(int x) {
        StringBuilder stringBuilder = new StringBuilder().append((x)).reverse();
        int result = 0;
        try {
            result = (x > 0 ? 1 : -1) * Integer.valueOf(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == x;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else {
            String prefix = "";
            for (int i = 0, len = strs[0].length(); i < len; i++) {
                String pre = strs[0].substring(0, i + 1);
                for (int j = 1, length = strs.length; j < length; j++) {
                    if (!strs[j].startsWith(pre)) {
                        return prefix;
                    }
                }
                prefix = pre;
            }
            return prefix;
        }
    }

    @Run
    public void longestCommonPrefix(CodeActivity codeActivity) {
        codeActivity.showText(longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
    }

    // "babad"
    public String longestPalindrome(String s) {
        int len = s.length();
        String current = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String cur = s.substring(i, j + 1);
                stringBuilder.append(cur);
                if (cur.equals(stringBuilder.reverse().toString())) {
                    if (cur.length() >= current.length()) {
                        current = cur;
                    }
                }
                stringBuilder.delete(0, cur.length());
            }
        }
        return current;
    }

    @Run
    public void longestPalindrome(CodeActivity codeActivity) {
        //        codeActivity.showText(longestPalindrome(
        //                "cwziydanrqvsdtvnnqgjnbrvvwxwqojeqgxhwxdoktjktulemwpbeqscbbtbfvkxsrjetfdrovcrdwzfmnnihtgxybuairswfewvpuscocqifuwylhssldpjrawqdrbvkykpaggspbfrulcktpbofchzikhzxhpocgvdbwpewpywsgqbczmamprklaoovcfecwchhmsaqkhvuvvzjblmgvqpqtnlipgqsanvovylpmxlmxvymppdykphhaamtxjnnlsqfwjwhyywgurteaummwhvavxbcpgrfffxrowluqmqjaugryxdmwvyokdcfcvcytxpixbvwrdgzctejdoaavgtezexmvxgrkpnayvfarkyoruofqmpnsqdzojxqrjsnfwsbzjmaoigytygukqlrcqaxazvmytgfghdczvzphfdbnxtklaiqqsotavdmhiaermluafheowcobjqmrkmlzyas"));
        codeActivity.showText(longestPalindrome("babad"));
    }

    public static String reverse1(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        String left = s.substring(0, length / 2);
        String right = s.substring(length / 2, length);
        return reverse1(right) + reverse1(left);
    }

    public int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }
        s = s.trim();
        int len = s.length();
        if(len == 0) {
            return 0;
        }
        int end = 0;
        for (int i = 0; i < len; i++) {
            char character = s.charAt(i);
            if (Character.isDigit(character) || character == '-' || character == '+') {
                end = i;
            } else {
                break;
            }
        }
        s = s.substring(0, end + 1);
        len = s.length();
        int result = 0;
        boolean negative = false;
        int limit = -Integer.MAX_VALUE;
        int i = 0;
        int multmin;
        int digit;
        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+') {
                return 0;
            }

            if (len == 1) { // Cannot have lone "+" or "-"
                return 0;
            }
            i++;
        }
        multmin = limit / 10;
        while (i < len) {
            digit = Character.digit(s.charAt(i++), 10);
            if (digit < 0) {
                return 0;
            }

            if (result <= multmin) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result *= 10;
            result -= digit;
        }
        return negative ? result : -result;
    }

    @Run
    public void myAtoi(CodeActivity codeActivity) {
        codeActivity.showText(myAtoi("2147483646") + "");
    }

}
