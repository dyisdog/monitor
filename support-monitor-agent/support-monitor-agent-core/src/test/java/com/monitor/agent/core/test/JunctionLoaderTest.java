package com.monitor.agent.core.test;

import com.support.monitor.agent.core.matcher.IJunctionLoader;
import com.support.monitor.agent.core.matcher.JunctionLoaderFactory;
import org.junit.Test;

public class JunctionLoaderTest {

    @Test
    public void junctionLoaderTest() {
        IJunctionLoader loader = JunctionLoaderFactory.builder("path");
        System.out.println(loader.ignoreJunction());
    }
}
