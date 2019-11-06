<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script type="application/javascript">
    $(function () {
        $("#star-show-table").jqGrid(
            {
                url: '${pageContext.request.contextPath}/star/findAll',
                datatype: "json",
                styleUI: "Bootstrap",
                height: 300,
                colNames: ['id', '艺名', '真名', '照片', '性别', '生日'],
                colModel: [
                    {name: 'id', width: 55, hidden: true},
                    {name: 'nickname', align: 'left', editable: true},
                    {name: 'realname', align: 'left', editable: true},
                    {
                        name: 'photo',
                        align: 'left',
                        editable: true,
                        edittype: "file",
                        formatter: function (value, option, rows) {
                            return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/star/img/" + rows.photo + "'>";
                        }
                    },
                    {name: 'sex', align: 'left', editable: true, edittype: "select", editoptions: {value: "男:男;女:女"}},
                    {name: 'bir', align: 'left', editable: true, edittype: "date"},
                ],
                rowNum: 2,
                autowidth: true,
                rowList: [2, 3, 5, 8],
                pager: '#star-page',
                viewrecords: true,
                subGrid: true,
                caption: "所有明星列表",
                editurl: "${pageContext.request.contextPath}/star/edit",
                subGridRowExpanded: function (subgrid_id, id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                        "<div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url: "${pageContext.request.contextPath}/user/findUsersByStarId?starId=" + id,
                            datatype: "json",
                            colNames: ['编号', '用户名', '昵称', '头像', '电话', '性别', '地址', '签名'],
                            colModel: [
                                {name: "id"},
                                {name: "username"},
                                {name: "nickname"},
                                {
                                    name: "photo",
                                    editable: true,
                                    edittype: "file",
                                    formatter: function (value, option, rows) {
                                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/user/img/" + rows.photo + "'>";
                                    }
                                },
                                {name: "phone"},
                                {name: "sex"},
                                {name: "address"},
                                {name: "sign"}
                            ],
                            styleUI: "Bootstrap",
                            rowNum: 2,
                            pager: pager_id,
                            autowidth: true,
                            height: '100%'
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit: false,
                            add: false,
                            del: false,
                            search: false
                        });
                },
            }).jqGrid('navGrid', '#star-page', {edit: true, add: true, del: true, search: false}, {
                //控制修改
                closeAfterEdit: true,
                beforeShowForm: function (fmt) {
                    fmt.find("#sex").attr("disabled", true),
                        fmt.find("#bir").attr("disabled", true);
                    fmt.find("#realname").attr("disabled", true);
                }
            }, {
                //控制添加
                closeAfterAdd: true,
                afterSubmit: function (data) {
                    var status = data.responseJSON.status;
                    var id = data.responseJSON.message;
                    if (status) {
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/star/upload",
                            type: "post",
                            fileElementId: "photo",
                            data: {id: id},
                            success: function (response) {
                                //自动刷新jqgrid表格
                                $("star-show-table").trigger("reloadGrid");
                            }
                        });
                    }
                    return "123";
                }
            }
        );
    });
</script>


<div class="panel panel-info">
    <div class="panel panel-heading">
        所有明星列表
    </div>
    <url class="nav nav-tabs">
        <li class="active"><a href="#">明星信息</a></li>
    </url>
    <table id="star-show-table"></table>
    <div id="star-page" style="height: 40px"></div>
</div>