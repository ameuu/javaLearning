package lab3;

import org.apache.commons.collections.Transformer;

import java.net.URL;
import java.net.URLClassLoader;

public class Index {
    private String name;
    private ClassLoader classLoader;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) throws Exception{
        Transformer[] transformers = new Transformer[]{};

        System.out.println(Class.forName(transformers.getClass().getName()));


        URL[] urls = ((URLClassLoader) Transformer.class.getClassLoader()).getURLs();
        Index index = new Index();
        index.classLoader =new URLClassLoader(urls);

        System.out.println(index.classLoader.loadClass(transformers.getClass().getName()));


    }
}
