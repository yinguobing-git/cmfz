<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="application/javascript">
    $(function () {
        $("#banner-show-table").jqGrid({
            url: "${pageContext.request.contextPath}/figure/findAll",
            datatype: "json",
            colNames: ['id', '图片名', '图片路径', '描述', '状态', '上传时间'],
            colModel: [
                {name: 'id', width: 55, hidden: true, editable: true},
                {name: 'name', width: 80, align: 'left', editable: true},
                {
                    name: 'imgPath',
                    width: 90,
                    align: 'left',
                    editable: true,
                    edittype: "file",
                    formatter: function (value, option, rows) {
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/figure/img/" + rows.imgPath + "'>";
                    }
                },
                {name: 'description', width: 60, align: 'left', editable: true},
                {name: 'status', editable: true, edittype: "select", editoptions: {value: "正常:正常;冻结:冻结"}},
                {name: 'up_date', width: 60, align: 'left'}
            ],
            height: 250,
            autowidth: true,
            styleUI: "Bootstrap",
            rowNum: 3,
            rowList: [3, 5, 10],
            pager: '#banner-page',
            sortname: 'id',
            viewrecords: true,
            editurl: "${pageContext.request.contextPath}/figure/edit"
        }).navGrid("#banner-page", {edit: true, add: true, del: true, search: false}, {
            //控制修改
            //操作完成后关闭窗口
            closeAfterEdit: true,
            beforeShowForm: function (fmt) {
                fmt.find("#img_path").attr("disabled", true);
            }, afterSubmit: function (data) {
                console.log(data);
                var status = data.responseJSON.status;
                var id = data.responseJSON.message;
                if (status) {
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/figure/upload",
                        type: "post",
                        fileElementId: "imgPath",
                        data: {id: id},
                        success: function (response) {
                            //自动刷新jqgrid表格
                            $("#banner-show-table").trigger("reloadGrid");
                        }
                    });
                }
                return "123";
            }
        }, {
            //控制添加
            closeAfterAdd: true,
            afterSubmit: function (data) {
                console.log(data);
                var status = data.responseJSON.status;
                var id = data.responseJSON.message;
                if (status) {
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/figure/upload",
                        type: "post",
                        fileElementId: "imgPath",
                        data: {id: id},
                        success: function (response) {
                            //自动刷新jqgrid表格
                            $("#banner-show-table").trigger("reloadGrid");
                        }
                    });
                }
                return "123";
            }
        });
    })
</script>
<div class="panel panel-info">
    <div class="panel panel-heading">
        <h3>所有的轮播图</h3>
    </div>

    <url class="nav nav-tabs">
        <li class="active"><a href="#">轮播图信息</a></li>
    </url>
    <table id="banner-show-table"></table>
    <div id="banner-page" style="height: 40px"></div>
</div>

