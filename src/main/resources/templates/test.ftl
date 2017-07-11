<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>测试FreeMarker</title>
</head>
<body>

<p>姓名：${name}</p>
<p>年龄：${age}</p>
<p>性别：<#if sex==0> 女 <#elseif sex==1> 男 <#else> 保密 </#if></p>

</body>
</html>