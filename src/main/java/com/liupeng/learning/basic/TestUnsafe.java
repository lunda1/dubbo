package com.liupeng.learning.basic;

import com.alibaba.fastjson.JSON;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author oliver.liu
 * @Date 2019/6/11 11:33
 */
public class TestUnsafe {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        //
        //unsafe = sun.misc.Unsafe.getUnsafe();

        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setAge(18);
        player.setName("Bob");
        for (Field field : Player.class.getDeclaredFields()) {
            System.out.println(field.getName()+":  对应的内存偏移地址"+unsafe.objectFieldOffset(field));
        }

        System.out.println("-----------------------");

        long ageOffset = 12L;
        System.out.println(unsafe.compareAndSwapInt(player, ageOffset,18,20));
        System.out.println("age修改后的值： "+player.getAge());
        System.out.println("-----------------------");

        for (Method m : Player.class.getDeclaredMethods()) {
            System.out.println(m.getName());
        }

        System.out.println("-----------------------");
        for (Method m : Player.class.getMethods()) {
            System.out.println(m.getName());
        }

        int[] arr = new int[]{11,22,33};
        System.out.println("-----------------------");
        int base = unsafe.arrayBaseOffset(arr.getClass());
        int scale = unsafe.arrayIndexScale(arr.getClass());
        int shift = 31 - Integer.numberOfLeadingZeros(scale);
        System.out.println(base);
        System.out.println(scale);
        System.out.println(shift);
        System.out.println(JSON.toJSONString(arr[1]));
        System.out.println(unsafe.getIntVolatile(arr,(1<<shift)+base));
        System.out.println(unsafe.getIntVolatile(arr,(1*scale)+base));
    }
}

class Player{
    //必须是int类型而不是Integer才可以直接根据地址进行CAS操作
    private int age;
    private String name;
    private Player[] players;

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void m1(){}

    protected void m2(){}

    private void m3(){}
}
