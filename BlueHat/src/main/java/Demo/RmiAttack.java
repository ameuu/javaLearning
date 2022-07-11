package Demo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiAttack {
    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);
            RmiInterface rmiInterface = (RmiInterface) registry.lookup("hello");
            String name = "Ameuu";
            System.out.println(rmiInterface.hello(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
