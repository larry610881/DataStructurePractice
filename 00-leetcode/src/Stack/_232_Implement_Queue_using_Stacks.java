package Stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 */
public class _232_Implement_Queue_using_Stacks {
    private Stack<Integer> inStack;
    private Stack<Integer>outStack ;
    public _232_Implement_Queue_using_Stacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();

    }

    //enQueue
    public void push(int x) {
        inStack.push(x);
    }

    //deQueue
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    //front
    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.empty()&& outStack.isEmpty();
    }

    private void checkOutStack(){
        if(outStack.isEmpty()){
            while (!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
    }
}
