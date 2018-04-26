package com.liupeng.learning.annotation;

@MyAnnotation(id=3,msg="qwe")
public class AnnotationTest {
    public static void main(String[] args) {
        MyAnnotation a = AnnotationTest.class.getAnnotation(MyAnnotation.class);
        System.out.println("id:"+a.id()+"; msg:"+a.msg());
    }
}
