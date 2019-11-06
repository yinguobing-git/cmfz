<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Regist Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="assets/js/jquery-2.2.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
    <script src="assets/js/jquery.validate.min.js"></script>
    <script>
        //表单验证
        $.extend($.validator.messages, {
            required: "<span style='color:red'>这是必填字段</span>",
            minlength: $.validator.format("<span style='color:red'>最少{6}个字符</span>"),
            maxlength: $.validator.format("<span style='color:red'>最多{50}个字符</span>"),
        });
        //表单提交
        $(function () {
            $("#registButtonId").click(function () {
                var isok = $("#registForm").valid();
                if (isok) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/regist",
                        type: "POST",
                        data: $("#registForm").serialize(),
                        dataType: "json",
                        success: function (data) {
                            if (data.status) {
                                location.href = "${pageContext.request.contextPath}/login/login.jsp"
                            } else {
                                $("#error-message").html("<font color='red'>" + data.message + "<font>");
                            }
                        }
                    })
                }
            })
        })
        //点击发送验证码触发的ajax请求
        $(function () {
            $("#send-code").click(function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/admin/sendMessage",
                    type: "POST",
                    data: "phone=" + $('#form-phone').val(),
                    dataType: "json",
                    <!--这里有问题  传参-->
                    success: function (data) {
                        if (data.status) {

                        } else {
                            $("#error-message").html("<font color='red'>" + data.message + "<font>");
                        }
                    }
                })

            })
        })
    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Regist Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your message to regist:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="${pageContext.request.contextPath}/admin/login" method="get"
                              class="login-form" id="registForm">
                            <span id="error-message"></span>
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="name" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" required>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       minlength="6" class="form-password form-control" id="form-password" required
                                       minlength>
                            </div>
                            <div class="form-group">
                                <label class="sr-only">Phone</label>
                                <input type="text" name="phone" placeholder="请输入手机号..."
                                       minlength="11" class="form-control" id="form-phone" required>
                            </div>
                            <div class="form-group">
                                <!--点击获取验证码的按钮--><!--发送ajax请求获取验证码-->
                                <input type="button" id="send-code" style="height: 48px" value="获取验证码">
                                <!--输入验证码的文本框-->
                                <input style="padding-left:20px;width: 287px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="test" name="code" id="form-code" placeholder="请输入验证码..." required>
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                                   id="registButtonId" value="注册">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>
