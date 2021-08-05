package com.ww.reflect.application;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ScanJarUtils {

    private static StringBuilder sb;
    
    /**
     * 通过反射扫描Jar下类，并获取类属性、方法信息
     * @param jar
     * @throws IOException 
     */
    @SuppressWarnings("resource")
    public static void getJar(String jar) throws IOException {
        try {
            File file = new File(jar);
            URL url = file.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[] { url }, Thread.currentThread().getContextClassLoader());
            
            JarFile jarFile = new JarFile(jar);
            Enumeration<JarEntry> entries = jarFile.entries();
            JarEntry jarEntry;
            
            sb = new StringBuilder();
            
            while (entries.hasMoreElements()) {
                jarEntry = (JarEntry) entries.nextElement();
                
                if (jarEntry.getName().indexOf("META-INF") < 0) {
                    String classFullName = jarEntry.getName();
                    if (classFullName.indexOf(".class") < 0) {
                        classFullName = classFullName.substring(0, classFullName.length() - 1);
                    } else {
                        // 去除后缀.class
                        String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                        Class<?> loadClass = classLoader.loadClass(className);
                        sb.append("类名\t:" + className);
                        System.out.println("类名\t:" + className);
                        
                        // 获取属性名
                        Field[] fields = loadClass.getDeclaredFields();
                        for (Field field : fields) {
                            sb.append("\n属性名\t:" + field.getName() + "\n");
                            System.out.println("属性名\t:" + field.getName());
                            sb.append("属性类型\t:" + field.getType() + "\n");
                            System.out.println("属性类型\t:" + field.getType());
                        }
                        
                        // 获得方法名
                        Method[] methods = loadClass.getMethods();
                        for (Method method : methods) {
                            if (method.toString().indexOf(className) > 0) {
                                sb.append("方法名\t:" + method.toString().substring(method.toString().indexOf(className)) + "\n");
                                System.out.println("方法名\t:" + method.toString().substring(method.toString().indexOf(className)));
                            }
                        }
                        sb.append("-----------------------------------------\n");
                        System.out.println("-----------------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sb.append("End");
            System.out.println("End");
            WriteFile.write(sb);
        }
    }
}
