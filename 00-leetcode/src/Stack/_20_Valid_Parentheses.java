package Stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class _20_Valid_Parentheses {
    private static HashMap<Character,Character> map = new HashMap<>();
    static {
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        int len =s.length();
        for(int i=0;i<len;i++){
            char c =s.charAt(i);
            if(map.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()) return false;
                char left = stack.pop();
                if(c!=map.get(left)) return false;
            }
        }
        return stack.isEmpty();
    }
    /**
     * [{()}]
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        while (s.contains("{}")||s.contains("[]")||s.contains("()")){
            s=s.replace("{}","");
            s=s.replace("()","");
            s=s.replace("[]","");

        }
        return s.isEmpty();

    }
}
