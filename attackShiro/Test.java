package com.govuln.shiroattack;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class Test {
    private String name = "Ameuu";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        try {
            System.out.println(PropertyUtils.getProperty(new Test(), "name"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
