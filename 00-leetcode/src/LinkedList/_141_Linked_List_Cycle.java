package LinkedList;

/**
 *  https://leetcode.com/problems/linked-list-cycle/
 */
public class _141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        if(head ==null || head.next==null) return false;
        ListNode left =head;
        ListNode right =head.next;
        while (left !=null &&right !=null){
            if(left ==right) return true;
            left =left.next;
            if(right.next !=null){
                right =right.next.next;
            }else{
                return  false;
            }

        }
        return  false;
    }
}
