package com.hello;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerDemo {
    private static final String TEMPLATE_PATH = "src/main/java/com/hello/templates";
    private static final String CLASS_PATH = "src/main/java/com/hello";

    public static void main(String[] args) {
        // 创建freemarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try{
            // 获取模板路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", "com.hello");
            dataMap.put("className","AutoCodeDemo");
            dataMap.put("helloWorld", "通过简单的<代码自动生产程序>演示FreeMaker的HelloWorld！");
            // 加载模板文件
            Template template = configuration.getTemplate("hello.ftl");
            // 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "AutoCodeDemo.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // 输出文件
            template.process(dataMap, out);
            System.out.println("---------------AutoCodeDemo.java 文件创建成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (null != out){
                    out.flush();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
