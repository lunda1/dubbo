package com.liupeng.learning.spi;

import com.liupeng.learning.Search;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        Iterator<Search> iter = loader.iterator();
        while (iter.hasNext()) {
            Search s = iter.next();
            s.search("test");
        }
    }
}
