<%@page pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <title>Document</title>
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script>
        var goEasy = new GoEasy({
            //发布的appkey
            appkey: "BC-e891702436bc4f33b60b17739844b6b2"
        });
        goEasy.publish({
            //当前的channel名称
            channel: "channel001",
            //发送（发布）的内容
            message: "新店开业,欢迎来玩!"
        });
    </script>


</head>
<body>

</body>
</html>