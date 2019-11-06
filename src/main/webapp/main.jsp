<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台主页面</title>
    <!-- CSS -->
    <%--引入bootstrap的样式--%>

    <link rel="stylesheet" href="statics/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="statics/boot/js/bootstrap.min.js"></script>

    <%--引入jqgrid--%>
    <script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>

    <%-- 引入kindeditor插件 --%>
    <script charset="UTF-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="kindeditor/lang/zh-CN.js"></script>


    <script src="echarts/echarts.min.js"></script>


    <!--引入goeasy的插件-->
    <script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <%----%>
    <script src="${pageContext.request.contextPath}/statics/jqgrid/js/ajaxfileupload.js"></script>
</head>
<body>
<!--导航条-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!--导航标题-->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法州后台系统
                <small>v1.0</small>
            </a>
        </div>

        <!--导航条内容-->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:<font color="aqua">${sessionScope.login.name}</font></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/exit">退出<span
                        class="glyphicon glyphicon-log-out"></span> </a></li>
            </ul>
        </div>
    </div>
</nav>
<!--页面主体内容-->
<div class="container-fluid">
    <!--row-->
    <div class="row">

        <!--菜单-->
        <div class="col-sm-2">

            <!--手风琴控件-->
            <div class="panel-group" id="accordion">

                <!--轮播图面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="figurePanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#figureLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="figureLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <ul class="list-group">
                                        <li class="list-group-item"><a
                                                href="javascript:$('#centerLayout').load('${path}/figure/figure_show.jsp');"
                                                id="btn">轮播图列表</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--类别面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="albumPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#albumLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="albumLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${path}/album/album-show.jsp')">专辑列表</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--文章面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="articlePanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#articleLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="articleLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${path}/article/article-show.jsp')">文章列表</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>


                <!--用户面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="userPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#userLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="userLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${path}/user/user-show.jsp');">用户列表</a>
                                </li>
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${path}/user/user_por.jsp');">注册情况</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--明星面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="startPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#startLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                明星管理
                            </a>
                        </h4>
                    </div>
                    <div id="startLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${path}/star/star_show.jsp');">明星列表</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--中心布局-->
        <div class="col-sm-10" id="centerLayout">
            <!--巨幕-->
            <div class="jumbotron">
                <h3>欢迎来到驰名法州后台管理系统</h3>
            </div>
            <!--轮播图-->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" align="center">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <div class="col-sm-8 col-sm-offset-2">
                            <img src="11.jpg" alt="...">
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-sm-8 col-sm-offset-2">
                            <img src="22.jpg" alt="...">
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="col-sm-8 col-sm-offset-2">
                            <img src="24.jpg" alt="...">
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
    <!--页脚-->
    <div class="panel panel-footer" align="center">
        <div>
            @百知教育
        </div>
    </div>
</div>
</body>
</html>