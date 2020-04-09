<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 2020/4/9
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <head>
        <%
            //front_war
            String path = request.getContextPath();
        %>
        <%@ include file="/WEB-INF/include-head.jsp" %>
        <link rel="stylesheet" href="ztree/zTreeStyle.css"/>
        <script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript" src="script/my-depart.js"></script>
        <script type="text/javascript">
            $(function(){
                //页面加载完成前加载
                initWholeTree();

                $("#departAddBtn").click(function () {
                    // 收集表单填写的数据
                    var name = $.trim($("#departAddModal [name='name']").val());
                    var icon = $("#departAddModal [name='icon']:checked").val();

                    if (name == null || name == "") {
                        layer.msg("请填写菜单项名称！");
                        return;
                    }



                    // 发送Ajax请求
                    $.ajax({
                        "url": "depart/save.json",
                        "type": "post",
                        "dataType": "json",
                        "data": {
                            "name": name,
                            "pid": window.departId,	// 当前操作的节点是新节点的父节点
                            "icon": icon
                        },
                        "success": function (response) {

                            var result = response.result;

                            if (result == "SUCCESS") {
                                layer.msg("操作成功！");

                                initWholeTree();
                            }

                            if (result == "FAILED") {
                                layer.msg(response.message);
                            }

                        },
                        "error": function (response) {
                            layer.msg(response.message);
                        }
                    });

                    $("#departAddModal").modal("hide");
                });

                $("#departEditBtn").click(function(){

                    // 收集表单填写的数据
                    var name = $.trim($("#departEditModal [name='name']").val());
                    var icon = $("#departEditModal [name='icon']:checked").val();

                    if(name == null || name == "") {
                        layer.msg("请填写菜单项名称！");
                        return ;
                    }



                    // 发送Ajax请求
                    $.ajax({
                        "url":"depart/update.json",
                        "type":"post",
                        "dataType":"json",
                        "data":{
                            "id":window.departId,
                            "pid":window.departPid,
                            "name":name,
                            "icon":icon
                        },
                        "success":function(response){

                            var result = response.result;

                            if(result == "SUCCESS") {
                                layer.msg("操作成功！");

                                initWholeTree();
                            }

                            if(result == "FAILED") {
                                layer.msg(response.message);
                            }

                        },
                        "error":function(response){
                            layer.msg(response.message);
                        }
                    });

                    $("#departEditModal").modal("hide");
                });

                $("#departRemoveBtn").click(function(){

                    $.ajax({
                        "url":"depart/remove/"+window.departId+".json",
                        "type":"post",
                        "data":{"random":Math.random()},
                        "dataType":"json",
                        "success":function(response){

                            if(response.result == "SUCCESS") {

                                layer.msg("操作成功！");

                                initWholeTree();
                            }

                            if(response.result == "FAILED") {
                                layer.msg(response.message);
                            }

                        },
                        "error":function(response){
                            layer.msg(response.message);
                        }
                    });

                    // 后续操作
                    $("#departConfirmModal").modal("hide");

                });

            });
        </script>
    </head>
<body>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 部门菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/include-modal-depart-add.jsp" %>
<%@ include file="/WEB-INF/include-modal-depart-edit.jsp" %>
<%@ include file="/WEB-INF/include-modal-depart-confirm.jsp" %>
</body>
</html>

