<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 2020/1/9
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="UTF-8">

<head>
    <%@ include file="/WEB-INF/include-head.jsp"%>
    <link rel="stylesheet" href="css/pagination.css" />
    <script type="text/javascript" src="script/jquery.pagination.js"></script>
    <script type="text/javascript" src="script/my-role.js"></script>
        <script type="text/javascript">
            $(function() {
                // 调用分页参数初始化方法
                initGlobalVariable();
                // 执行分页
                showPage();

                //查询分页
                $("#searchBtn").click(function(){
                    // 在单击响应函数中获取文本框中输入的数据
                    var keyword = $.trim($("#keywordInput").val());

                    // 验证输入数据是否有效
                    if(keyword == null || keyword == "") {
                        // 如果无效，提示，停止函数执行
                        layer.msg("请输入关键词！");
                        return ;
                    }

                    // 如果有效，赋值给window.keyword
                    window.keyword = keyword;

                    // 调用showPage()重新分页
                    showPage();
                });


                // 全选/全不选功能
                $("#summaryBox").click(function(){

                    // 1.获取当前checkbox的选中状态
                    var currentStatus = this.checked;

                    // 2.设置itemBox的选中状态
                    $(".itemBox").prop("checked",currentStatus);

                });


                // 给批量删除按钮绑定单击响应函数
                $("#batchRemoveBtn").click(function(){

                    // 获取被选中的itemBox数组长度
                    var length = $(".itemBox:checked").length;

                    // 如果长度为0，说明没有选择itemBox
                    if(length == 0) {
                        layer.msg("请至少选择一条！");
                        return ;
                    }

                    // 在全局作用域内创建roleIdArray
                    window.roleIdArray = new Array();

                    // 遍历$(".itemBox:checked")
                    $(".itemBox:checked").each(function(){

                        // 通过checkbox的roleid属性获取roleId值
                        var roleId = $(this).attr("roleid");

                        // 存入数组
                        window.roleIdArray.push(roleId);

                    });

                    // 调用函数打开模态框
                    showRemoveConfirmModal();
                });



                // 给确认模态框中的OK按钮绑定单击响应函数
                $("#confirmModalBtn").click(function(){

                    var requestBody = JSON.stringify(window.roleIdArray);

                    $.ajax({
                        "url":"role/batch/remove.json",
                        "type":"post",
                        "data":requestBody,
                        "contentType":"application/json;charset=UTF-8",
                        "dataType":"json",
                        "success":function(response){

                            var result = response.result;

                            if(result == "SUCCESS") {
                                layer.msg("操作成功！");

                                // 如果删除成功，则重新调用分页方法
                                showPage();
                            }

                            if(result == "FAILED") {
                                layer.msg(response.message);
                            }

                            // 不管成功还是失败，都需要关掉模态框
                            $("#confirmModal").modal("hide");

                        },
                        "error":function(response){
                            layer.msg(response.message);
                        }
                    });

                });


                // 针对.removeBtn这样动态生成的元素对象使用on()函数方式绑定单击响应函数
// $("动态元素所依附的静态元素").on("事件类型","具体要绑定事件的动态元素的选择器", 事件响应函数);
                $("#roleTableBody").on("click",".removeBtn", function(){
                    // 获取当前记录的roleId
                    var roleId = $(this).attr("roleId");

                    // 存入全局变量数组
                    window.roleIdArray = new Array();

                    window.roleIdArray.push(roleId);

                    // 打开模态框（后续所有操作都和批量删除一样）
                    showRemoveConfirmModal();
                });


                //编辑更新按钮
                $("#roleTableBody").on("click",".editBtn",function(){

                    // 1.获取当前按钮的roleId
                    window.roleId = $(this).attr("roleId");

                    // 2.获取当前按钮所在行的roleName
                    var roleName = $(this).parents("tr").children("td:eq(2)").text();

                    // 3.修改模态框中文本框的value值，目的是在显示roleName
                    $("#roleNameInputEdit").val(roleName);

                    // 4.打开模态框
                    $("#editModal").modal("show");
                });


            })
        </script>
</head>

<body>

<%@ include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <i class="glyphicon glyphicon-th"></i> 数据列表
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float: left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="keywordInput" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="searchBtn" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="batchRemoveBtn" type="button" class="btn btn-danger"
                            style="float: right; margin-left: 10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 批量删除
                    </button>
                    <button id="addBtn" type="button" class="btn btn-primary"
                            style="float: right;">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear: both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                            <tr>
                                <th width="30">#</th>
                                <th width="30"><input id="summaryBox" type="checkbox"></th>
                                <th>名称</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <%--        <tr>
                                <td>1</td>
                                <td><input type="checkbox"></td>
                                <td>PM - 项目经理</td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                    <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                    <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            </tbody>--%>
                            <tbody id="roleTableBody"></tbody>
           <%--                 <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li class="disabled"><a href="#">上一页</a></li>
                                        <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">4</a></li>
                                        <li><a href="#">5</a></li>
                                        <li><a href="#">下一页</a></li>
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>--%>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination">
                                        <!-- 这里显示分页 -->
                                    </div>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/include-modal-role-confirm.jsp" %>
<%@ include file="/WEB-INF/include-modal-role-edit.jsp" %>
</body>
</html>
