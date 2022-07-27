package lab7;

import lab1.Utils;

import javax.management.BadAttributeValueExpException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;


public class Index {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String data = scanner.next();
        byte[] b = Utils.hexStringToBytes(data);
        InputStream inputStream = new ByteArrayInputStream(b);
        MyObjectInputStream myObjectInputStream = new MyObjectInputStream(inputStream);
        String name = myObjectInputStream.readUTF();
        int year = myObjectInputStream.readInt();
        if (name.equals("SJTU") && year == 1896)
            myObjectInputStream.readObject();

    }
}
