package JRMPTest;

import lab1.Utils;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.Scanner;

public class Index {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        byte[] obj = Utils.hexStringToBytes(input);
        ByteArrayInputStream bis = new ByteArrayInputStream(obj);
        ObjectInputStream ois = new ObjectInputStream(bis);
        ois.readObject();
//        DGCImpl_Stub
    }
}
