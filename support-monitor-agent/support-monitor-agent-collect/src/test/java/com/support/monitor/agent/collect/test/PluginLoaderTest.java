package com.support.monitor.agent.collect.test;

import com.support.monitor.agent.collect.plugin.JarPluginLoader;
import com.support.monitor.agent.collect.plugin.PluginLoaderProxy;
import org.junit.Test;

public class PluginLoaderTest {


    private PluginLoaderProxy pluginLoaderProxy = new PluginLoaderProxy();

    private JarPluginLoader jarPluginLoader = new JarPluginLoader();

    @Test
    public void loadPlugin() {
//        List<PluginLoadResult> defines = pluginLoaderProxy.loadPlugin();
//        System.out.println(defines);

//        List<JarFile> jarFiles = jarPluginLoader.loadPlugin("E:\\plugins");

//        jarFiles.forEach(jarFile -> {
//            Enumeration<JarEntry> enume = jarFile.entries();
//            while (enume.hasMoreElements()) {
//                JarEntry element = enume.nextElement();
//
//                String name = element.getName();
//                if (name.toUpperCase().endsWith(".CLASS")) {
//                    try {
//                        Class in = Class.forName(StringUtils.substringBeforeLast(name, ".class").replaceAll("/", "."));
//                        System.out.println(in);
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });


    }
}
