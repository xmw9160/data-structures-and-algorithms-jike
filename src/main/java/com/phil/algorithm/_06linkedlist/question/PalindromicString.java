package com.phil.algorithm._06linkedlist.question;

/**
 * “回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
 * <p>
 * 如何判断一个字符串是否是回文字符串的问题，我想你应该听过，我们今天的思题目就是基于这个问题的改造版本。
 * 如果字符串是通过单链表来存储的，那该如何来判断是一个回文串呢？你有什么好的解决思路呢？相应的时间空间复杂度又是多少呢？
 * <p>
 * 使用快慢两个指针找到链表中点，慢指针每次前进一步，快指针每次前进两步。
 * 在慢指针前进的过程中，同时修改其 next 指针，使得链表前半部分反序。最后比较中点两侧的链表是否相等
 * https://github.com/andavid/leetcode-java/blob/master/note/234/README.md
 * <p>
 * 1 快慢指针定位中间节点
 * 2 从中间节点对后半部分逆序[逆序是重点]
 * 3 前后半部分比较，判断是否为回文
 * 4 后半部分逆序复原
 * 时间复杂度On, 空间复杂度O1
 * 如果是双向链表，时间效率更高，看了下LinkedList，底层也是用双向链表实现
 *
 * 单向链表:
 * 1. 快慢指针 + 反序
 * 2. 快慢指针 + 栈
 *
 * 双向链表:
 * 前后指针遍历
 *
 * @author mingwei.xia
 * @date 2019-08-06 16:39
 */
public class PalindromicString {
    public static void main(String[] args) {
        String text = "noon";

        int i = 0;
        int j = text.length() - 1;

        String[] split = text.split("");
        for (; i < j; i++, j--) {
            if (!split[i].equals(split[j])) {
                System.out.println(text + " 不是回文串..");
                return;
            }
        }
        System.out.println(text + " 是回文串...");
    }
}
