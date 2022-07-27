package Test;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello world");
        Map map = new HashMap();
        map.put("a","b");
        map.put("a","c");
        System.out.println(map.get("a"));
    }
}
