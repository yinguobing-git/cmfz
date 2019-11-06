<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="../echarts/echarts.min.js"></script>

</head>
<body>
<div id="main" style="width: 600px;height: 400px;">

</div>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '注册情况分析表'
        },
        //相关提示
        tooltip: {},
        //图示注解
        legend: {
            data: ['男', '女']
        },
        //水平方向坐标
        xAxis: {
            data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
        },
        //垂直方向坐标
        yAxis: {},
        series: [{
            //和图示注解一致
            name: '男',
            //柱状图
            type: 'line',
            //销量值
            data: [5, 20, 36, 10, 10, 20]
        }, {
            //和图示注解一致
            name: '女',
            //柱状图
            type: 'line',
            //销量值
            data: [18, 6, 44, 3, 24, 10]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
</body>
</html>