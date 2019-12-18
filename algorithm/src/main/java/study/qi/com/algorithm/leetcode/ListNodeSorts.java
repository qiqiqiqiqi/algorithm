package study.qi.com.algorithm.leetcode;

/**
 * Created by feng on 2019/12/2.
 */


public class ListNodeSorts {

    /**
     * 定义的节点类
     *
     * @author fxjzzyo
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 交换两个节点的值
     *
     * @param i
     * @param j
     */
    public static void swap(ListNode i, ListNode j) {
        int temp = i.val;
        i.val = j.val;
        j.val = temp;
    }

    /**
     * 冒泡排序
     *
     * @param head
     * @return
     */
    public static ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = null;//node p 已排序区域的头结点

        boolean isExchanged = true;
        while (isExchanged && p != head.next) {

            ListNode q = head;
            isExchanged = false;
            for (; q.next != null && q.next != p; q = q.next) {//q.next != null判断是否是已经到达链尾，q.next != p判断是否是未排序区域的链尾
                if (q.val > q.next.val) {
                    int temp = q.val;
                    q.val = q.next.val;
                    q.next.val = temp;
                    isExchanged = true;
                }
            }
            p = q;
        }
        return head;
    }

    /**
     * 插入排序
     * 插入排序（算法中是直接交换节点，时间复杂度O（n^2）,空间复杂度O（1））
     *
     * @param head
     * @return
     */
    public static ListNode insertSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pStart = new ListNode(0);//添加辅助的头节点
        pStart.next = head;
        ListNode p = head.next;
        ListNode pend = head;
        while (p != null) {
            ListNode temp = pStart.next;
            ListNode pre = pStart;
            while (temp != p && p.val > temp.val) {
                temp = temp.next;
                pre = pre.next;
            }
            if (temp == p) pend = p;
            else {
                pend.next = p.next;
                p.next = temp;
                pre.next = p;
            }
            p = pend.next;
        }
        head = pStart.next;
        return head;
    }

    /**
     * 选择排序
     * 选择排序（算法中只是交换节点的val值，时间复杂度O（n^2）,空间复杂度O（1））
     *
     * @param head
     * @return
     */
    public static ListNode selectSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pStart = new ListNode(0);//添加辅助的头节点
        pStart.next = head;
        ListNode tailNode = pStart;//指向已排好序的尾部节点

        while (tailNode.next != null) {
            ListNode minNode = tailNode.next;
            ListNode p = tailNode.next.next;
            //寻找未排序部分的最小节点
            while (p != null) {
                if (p.val < minNode.val) {
                    minNode = p;
                }
                p = p.next;
            }
            //交换值
            int temp = tailNode.next.val;
            tailNode.next.val = minNode.val;
            minNode.val = temp;

            tailNode = tailNode.next;
        }
        head = pStart.next;
        return head;
    }

    private static ListNode partation(ListNode head, ListNode tail) {
        int ppv = head.val;
        ListNode p = head;
        for (ListNode i = p.next; i != tail; i = i.next) {
            if (i.val < ppv) {
                p = p.next;
                if (i != p) {
                    swap(i, p);
                }
            }
        }
        swap(p, head);
        return p;
    }

    public static void quickSort(ListNode head, ListNode tail) {
        if (head != tail && head.next != tail) {
            ListNode p = partation(head, tail);
            quickSort(head, p);
            quickSort(p.next, tail);
        }
    }

    /**
     * 快速排序1
     * 快速排序1（算法只交换节点的val值，平均时间复杂度O（nlogn）,不考虑递归栈空间的话空间复杂度是O（1））
     *
     * @param head
     * @return
     */
    public static ListNode quickSort(ListNode head) {
        if (head == null || head.next == null) return head;
        quickSort(head, null);
        return head;
    }

    public static ListNode partation2(ListNode preNode, ListNode head, ListNode tail) {
        int ppv = head.val;
        //用两个链表分别记录比基准小的节点 和 比基准大的节点
        ListNode small = new ListNode(0);
        ListNode big = new ListNode(0);
        ListNode s = small, b = big;

        for (ListNode i = head.next; i != tail; i = i.next) {
            if (i.val < ppv) {
                s.next = i;
                s = s.next;
            } else {
                b.next = i;
                b = b.next;
            }
        }
        //将前驱节点、小链表、基准节点、大链表、尾巴 连接起来
        b.next = tail;
        s.next = head;
        head.next = big.next;
        preNode.next = small.next;

        return head;

    }

    public static void quickSort2(ListNode preNode, ListNode head, ListNode tail) {
        if (head != tail && head.next != tail) {
            ListNode p = partation2(preNode, head, tail);
            quickSort2(preNode, preNode.next, p);
            quickSort2(p, p.next, tail);
        }
    }

    /**
     * 快速排序2
     * 快速排序2（算法交换链表节点，平均时间复杂度O（nlogn）,不考虑递归栈空间的话空间复杂度是O（1））
     *
     * @param head
     * @return
     */
    public static ListNode quickSort2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode preNode = new ListNode(0);//添加辅助的头节点
        preNode.next = head;
        quickSort2(preNode, head, null);
        return preNode.next;
    }

    /**
     * 合并两个链表为有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode pNode = new ListNode(0);
        ListNode p = pNode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return pNode.next;
    }

    /**
     * 归并排序
     * 归并排序（算法交换链表节点，时间复杂度O（nlogn）,不考虑递归栈空间的话空间复杂度是O（1））
     *
     * @param head
     */
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        //“快慢指针法”将链表分成两部分
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;//后半部分
        slow.next = null;//断开
        //递归处理两段子链表
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(fast);
        //合并两个子链表为有序链表
        return merge(l1, l2);
    }


    /**
     * 打印链表
     *
     * @param head
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        //构建链表
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(1);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println("---------排序前-------");
        printList(head);

        //排序
//		head = mergeSort(head);
		head = bubbleSort(head);
//		head = insertSort(head);
//		head = selectSort(head);
//		head = quickSort(head);
//        head = quickSort2(head);

        System.out.println("---------排序后-------");
        printList(head);
    }

}

