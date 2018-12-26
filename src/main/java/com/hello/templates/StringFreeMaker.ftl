${"Hello ${name}!"} / ${"Hello" + name +"!"}
<#assign cname = r"特殊字符完成输出(http:\www.baidu.com)">
<#--${cname}-->

字符串截取：通过下标直接获取下标对应的字母：${name[2]}
起点下标..结尾下标截取字符串:${name[0..5]}

算数运算
<#-- 支持"+"、"-"、"*"、"/"、"%"运算符 -->
<#assign number1 = 10>
<#assign number2 = 5>
"+":${number1 + number2}
"-":${number1 - number2}
"*":${number1 * number2}
"/":${number1 / number2}
比较运算符:
<#if number1 + number2 gte 12 || number1 - number2 lt 6>
"*" : ${number1 * number2}
<#else>
"/" : ${number1 / number2 }
</#if>

内建函数：
<#assign data = "abc123456">
第一个字母大写：${data?cap_first}
所有字母小写：${data?lower_case}
所有字母大写：${data?upper_case}
<#assign floatData = 12.34>
数值取整数：${floatData?int}
获取集合的长度：${users?size}
<#--时间格式化：${dateTime?string("yyyy-MM-dd")}-->

空判段和对象集合：
<#if users ??>
<#list users as user>
${user.id} ----- ${user.name}
</#list>
<#else >
${user!"变量为空则给一个默认值"}
</#if>


Map集合:
<#assign mapData={"name":"程序员","salary":15000}>
直接通过Key获取Value值：${mapData["name"]}
通过key遍历Map：
<#list mapData?keys as key>
key: ${key}----Value: ${mapData["name"]}
</#list>
<#list mapData?values as value>
${value}
</#list>

List集合:
<#assign listData = ["ITDragon", "Blog" , "Is", "Cool"]>
<#list listData as value>
    ${value}
</#list>

include指令:
引入其他文件:<#include "otherFreeMaker.ftl" />

macro宏指令:
<#macro mo>
    定义无参的宏macro-- ${name}
</#macro>
使用宏macro:<@mo />
<#macro moArgs a b c>
${a+b+c}
</#macro>

使用带参的宏macro: <@moArgs a=1 b=2 c=3 />

引入命名空间:
<#import "otherFreeMaker.ftl" as otherFtl>
${otherFtl.otherName}
<@otherFtl.addMethod a=10 b=20 />
<#assign otherName="修改otherFrrMaker.ftl中的otherName变量 "/>
${otherFtl.otherName}
<#assign otherName = "修改otherFrrMaker.ftl中的otherName变量" in otherFtl />
${otherFtl.otherName}