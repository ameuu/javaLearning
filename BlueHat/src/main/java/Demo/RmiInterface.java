package Demo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiInterface extends Remote {
    public String hello(String param) throws RemoteException;
}

