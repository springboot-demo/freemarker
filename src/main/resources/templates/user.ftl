<#assign S_URL=request.contextPath />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户模型</title>
    <style>
        table,
        .box {
            width: 600px;
            margin: 10px auto;
            text-align: center;
            border: 1px solid #ccc;
            border-collapse: collapse;
        }

        td, th {
            border: 1px solid #ccc;
            text-align: center;
        }
    </style>
</head>
<body>

<table border="0" cellpadding="0" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>性别</th>
    </tr>
<#list items as item>
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.age}</td>
        <td><#if item.sex==0> 女 <#elseif item.sex==1> 男 <#else> 保密 </#if></td>
    </tr>
</#list>
</table>

<div class="box">
    <p><input type="text" name="name" id="name"></p>
    <p><input type="text" name="age" id="age"></p>
    <p><input type="text" name="sex" id="sex"></p>
    <input type="button" value="提交" id="btn">
</div>

<script src="${S_URL}/js/jquery.min.js"></script>
<script>
    $(function () {

        $("#btn").on('click', function () {
            var name = $("#name").val();
            var age = $("#age").val();
            var sex = $("#sex").val();

            $.ajax({
                url: '/api',
                type: 'post',
                data: {
                    name: name,
                    age: age,
                    sex: sex
                },
                success: function (res) {
                    console.log(res);
                }
            });
        });

    });
</script>
</body>
</html>