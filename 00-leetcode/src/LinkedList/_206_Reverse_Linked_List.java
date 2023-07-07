package LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            newHead =head;
            head =tmp;
        }
        return  newHead;
    }
    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp =new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        if(n<4) return res;
        for(int i=2;i<=n;i++){
            if(isPrime(i)){
                if(map.containsKey(n-i)){
                    tmp.add(map.get(n-i));
                    tmp.add(n-i);
                    res.add(tmp);
                    tmp.clear();
                }else{
                    map.put(n-i,i);
                }

            }
        }
        return res;
    }
    private boolean isPrime(int n){
        if(n==2) return true;
        for(int i=2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}
