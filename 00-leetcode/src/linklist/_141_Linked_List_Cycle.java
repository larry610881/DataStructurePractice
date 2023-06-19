package linklist;

/**
 *  https://leetcode.com/problems/linked-list-cycle/
 */
public class _141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow!=null &&  fast !=null){
            slow =slow.next;
            fast = slow.next.next;
            if(slow==fast) return  true;
        }
        return  false;
    }
}
