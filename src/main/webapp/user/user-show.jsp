<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="application/javascript">
    $(function () {
        $("#user-show-table").jqGrid({
            url: "${pageContext.request.contextPath}/user/findAllUsers",
            datatype: "json",
            colNames: ['用户名', '昵称', '手机号', '省份', '城市', '标签', '头像', '性别'],
            colModel: [
                {name: 'username', width: 55, hidden: true, editable: false},
                {name: 'nickname', width: 80, align: 'left', editable: true},
                {name: 'phone', width: 60, align: 'left', editable: true},
                {name: 'province', width: 60, align: 'left', editable: true},
                {name: 'city', width: 60, align: 'left', editable: true},
                {name: 'sign', width: 60, align: 'left', editable: true},
                {
                    name: 'photo',
                    width: 90,
                    align: 'left',
                    editable: true,
                    edittype: "file",
                    formatter: function (value, option, rows) {
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/user/img/" + rows.photo + "'>";
                    }
                },
                {name: 'sex', editable: true, edittype: "select", editoptions: {value: "男:男;女:女"}},
            ],
            height: 250,
            autowidth: true,
            styleUI: "Bootstrap",
            rowNum: 3,
            rowList: [3, 5, 10],
            pager: '#user-page',
            sortname: 'id',
            viewrecords: true,
            editurl: "${pageContext.request.contextPath}/user/edit"
        }).navGrid("#user-page", {edit: false, add: false, del: false, search: false}, {
            //控制修改
            //操作完成后关闭窗口
            closeAfterEdit: true,
            beforeShowForm: function (fmt) {
                fmt.find("#sex").attr("disabled", true);
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
                        url: "${pageContext.request.contextPath}/user/upload",
                        type: "post",
                        fileElementId: "photo",
                        data: {id: id},
                        success: function (response) {
                            //自动刷新jqgrid表格
                            $("#user-show-table").trigger("reloadGrid");
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
        <h3>所有的用户</h3>
    </div>

    <url class="nav nav-tabs">
        <li class="active"><a href="#">用户信息</a></li>
        <!--用户导出的方法-->
        <li><a href="${pageContext.request.contextPath}/user/export">用户导出</a></li>
    </url>
    <table id="user-show-table"></table>
    <div id="user-page" style="height: 40px"></div>
</div>

