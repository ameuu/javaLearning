package Demo;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Rmi extends UnicastRemoteObject implements RmiInterface {
    private String hello = "Hello: ";

    protected Rmi() throws RemoteException {
    }

    @Override
    public String hello(String param) throws RemoteException {
        return hello+param;
    }

    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.createRegistry(1099);
        RmiInterface rmiInterface = new Rmi();
        registry.bind("hello",rmiInterface);
        // Naming.bind("rmi://ip:port/hello",rmiInterface) 远程写法
        // Naming.bind("hello",rmiInterface) 本地写法
    }
}

