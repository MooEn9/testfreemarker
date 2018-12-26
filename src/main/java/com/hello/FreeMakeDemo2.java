package com.hello;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;

public class FreeMakeDemo2 {
    public static final String TEMPLATE_PATH = "src/main/java/com/hello/templates/";

    public static void main(String[] args) {
        // 创建freeMaker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // 获取模板路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("name", "mooen博客");
            dataMap.put("dateTime", new Date());

            List<user> users = new ArrayList<user>();
            users.add(new user("1","mooen"));
            users.add(new user("2", "17"));
            dataMap.put("users", users);
            // 加载模板文件
            Template template = configuration.getTemplate("StringFreeMaker.ftl");
            // 生成数据
            out = new OutputStreamWriter(System.out);
            // 输出文件
            template.process(dataMap, out);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
