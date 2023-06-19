package linklist;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class _206_Reverse_Linked_List {

    public ListNode reverseList(ListNode head) {
        if(head ==null||head.next==null) return head ;
        ListNode newHead = reverseList(head.next);
        head.next.next =head;
        head.next =null;
        return  newHead;
    }

    public ListNode reverseList2(ListNode head) {
        if(head ==null||head.next==null) return head ;
        ListNode newHead = null;
        while (head!=null){
            ListNode tmp = head.next;
            head.next = newHead;
            head =tmp;
        }
        return  newHead;
    }
}
