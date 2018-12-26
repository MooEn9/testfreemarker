# FreeMaker快速入门

> FreeMaker是一款顶顶大名的模板引擎，是**基于模板文件生成其他文本的通用工具**。Free Maker是一款用JAVA语言编写，非常适合作为web应用的框架

1. 创建一个FreeMaker中的Hello World程序。

   项目创建流程：

   * 第一步：创建一个maven项目导入 FreeMarker jar 包 
   * 第二步：创建目录templates，并创建一个 FreeMarker模版文件 hello.ftl 
   * 第三步：创建一个运行FreeMarker模版引擎的 FreeMarkerDemo.java 文件 
   * 第四步：运行main方法后刷新项目 

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.freemark</groupId>
       <artifactId>freemarkerStudy</artifactId>
       <version>0.0.1-SNAPSHOT</version>
       <packaging>war</packaging>
   
       <dependencies>
           <dependency>
               <groupId>org.freemarker</groupId>
               <artifactId>freemarker</artifactId>
               <version>2.3.20</version>
           </dependency>
       </dependencies>
       
   </project>
   ```

   ```java
   package ${classPath};
   // hello.ftl 
   public class ${className} {
       
       public static void main(String[] args) {
           System.out.println("${helloWorld}");
       }
   
   }
   ```

   ``` java
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
   
   ```

   运行之后的结果：产生了一个AutoCodeDemo.java类。其实不但可以产生java类，也可以产生xml文件

2. FreeMaker基本语法，在项目中也写了一个基本的FreeMaker的语法示例。

   ### 语法详解

   **数据类型**
   和java不同，FreeMarker不需要定义变量的类型，直接赋值即可。
   字符串： value = "xxxx" 。如果有特殊字符 string = r"xxxx" 。单引号和双引号是一样的。
   数值：value = 1.2。数值可以直接等于，但是不能用科学计数法。
   布尔值：true or false。
   List集合：list = [1,2,3] ; list=[1..100] 表示 1 到 100 的集合，反之亦然。
   Map集合：map = {"key" : "value" , "key2" : "value2"}，key 必须是字符串哦！
   实体类：和EL表达式差不多，直接点出来。

   **字符串操作**
   字符串连接：可以直接嵌套"hello,$name"；也可以用加号{"hello , " + name}

   字符串截取：string[index]。index 可以是一个值，也可以是形如 0..2 表示下标从0开始，到下标为2结束。一共是三个数。

   **比较运算符**
   == （等于），!= （不等于），gt（大于），gte（大于或者等于），lt（小于），lte（小于或者等于）。不建议用 >，< 可能会报错！
   一般和 if 配合使用

   **内建函数**
   FreeMarker 提供了一些内建函数来转换输出，其结构：变量?内建函数，这样就可以通过内建函数来转换输出变量。
   html： 对字符串进行HTML编码；
   cap_first： 使字符串第一个字母大写；
   lower_case： 将字符串转成小写；
   upper_case： 将字符串转成大写；
   size： 获得集合中元素的个数；
   int： 取得数字的整数部分。

   **变量空判断**
   ! 　　指定缺失变量的默认值；一般配置变量输出使用
   ?? 　判断变量是否存在。一般配合if使用 <#if value??></#if>

   **宏指令**
   可以理解为java的封装方法，供其他地方使用。宏指令也称为自定义指令，macro指令
   语法很简单：<#macro val > 声明macro </#macro>; 使用macro <@val />

   **命名空间**
   可以理解为java的import语句，为避免变量重复。一个重要的规则就是:路径不应该包含大写字母，使用下划线_分隔词语，myName --> my_name
   语法很简单：<#import "xxx.ftl" as val>