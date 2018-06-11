package com.liupeng.learning.javasist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class TestJavasist {
    public static void main(String[] args) throws Exception{
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.liupeng.learning.javasist.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"new inserted Hello.say():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}
