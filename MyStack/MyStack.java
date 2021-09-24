import java.util.ArrayList;
import java.util.HashMap;

public class MyStack<AnyType> {
    private ArrayList<AnyType> arr = new ArrayList<AnyType>();
    private int currIndex = 0;

    public void push(AnyType obj) {
        arr.add(obj);
        currIndex++;
    }

    public AnyType pop() {
        AnyType res;
        if (currIndex == 0)
            throw new IndexOutOfBoundsException("No elements in stack to pop");
        res = arr.get(currIndex - 1);
        arr.remove(currIndex - 1);
        currIndex--;
        return res;
    }

    public AnyType peek() {
        if (currIndex == 0)
            throw new IndexOutOfBoundsException("No elements in stack to peek");
        return arr.get(currIndex - 1);
    }

    public boolean isEmpty() {
        if (currIndex == 0)
            return true;
        else
            return false;
    }

    public void MyStack() {

    }

    public static void main(String[] args) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');
        map.put('<', '>');
        String str = "({[<>]})";
        MyStack<Character> stack = new MyStack<>();
        for (char c : str.toCharArray()) {
            if (c == '{' || c == '[' || c == '(' || c == '<') {
                stack.push(c);
            } else {
                if (map.get(stack.pop()) != c) {
                    System.out.println("!!!Wrong Pattern!!!");
                    break;
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("!!!Correct Pattern!!!");
        } else {
            System.out.println("!!!Wrong Pattern!!!");
        }
    }
}
