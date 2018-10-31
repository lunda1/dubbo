package com.liupeng.learning.juc;

import java.lang.ref.WeakReference;

public class TestWeakReference {
    public static void main(String[] args) {
        testWeakReference();
    }

    public static void testWeakReference(){
        Car car = new Car(1,"name1");
        WeakReference<Car> carWeakReference = new WeakReference<>(car);
        int i = 0;
        while(true){
            //System.out.println(car);
            if (carWeakReference.get() != null) {
                i++;
                System.out.println("Object is alive for "+i+" loops "+carWeakReference.get());
            } else {
                System.out.println("Object has been collected");
                break;
            }
        }
    }
}

class Car {
    Integer id;
    String name;

    public Car(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
