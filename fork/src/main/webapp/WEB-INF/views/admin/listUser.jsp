<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@page import="com.baicheng.fork.core.feature.orm.mybatis.Page" %>
<%@page import="com.baicheng.fork.domain.user.User" %>
<%
    Page<User> nowPage = (Page<User>) request.getAttribute("page");
    int maxPage = nowPage.getTotalPages();
    int cPage = nowPage.getPageNo();
    int endPage = cPage + 4;
    int startPage = cPage - 5;
    if (startPage < 1) {
        startPage = 1;
        endPage = 10;
    }
    if (endPage > maxPage) {
        endPage = maxPage;
        startPage = endPage - 9;
    }
    if (maxPage < 10) {
        startPage = 1;
        endPage = maxPage;
    }
    int num = nowPage.getPageSize();
    int startNo = (cPage - 1) * num;
%>
<c:set value="<%=num%>" var="num" scope="page"/>
<c:set value="<%=startNo%>" var="startNo" scope="page"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>百程 CRM</title>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="/fork/assets/img/favicon.ico"/>
    <link href="/fork/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/fork/assets/plugins/select2/select2_metro.css"/>
    <link href="/fork/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="/fork/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="/fork/assets/plugins/data-tables/DT_bootstrap.css">
    <link href="/fork/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css">

    <link href="/fork/assets/css/sweetalert.css" rel="stylesheet"/>
    <link href="/fork/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/fork/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/bootstrap-select.css"/>
    <link rel="stylesheet" href="/fork/assets/css/jquery-ui-1.10.3.full.min.css"/><!--日历、弹层-->
    <link rel="stylesheet" href="/fork/assets/css/crm.css"/>

    <script src="/fork/assets/scripts/ace-extra.min.js"></script>
    <style>
        .tab-group .single-group:first-child .relation {
            display: none;
        }

        .node-treeview_customer.node-selected {
            color: blue;
            background-color: #b0ddf7;
        }

        .page-header {
            padding-bottom: 10px;
        }

        .page-header p {
            margin-top: 10px;
            padding-left: 20px;
            color: #478fca;
            font-size: 14px;
            margin-bottom: 0;
        }

        .pagination {
            font-size: 12px;
        }
    </style>
</head>

<body>

<div class="main-container" id="main-container">

    <div class="main-container-inner">

        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                    <a href="/fork/member_system/customer_addgroup.html">首页</a>
                </li>
                <li class="active">系统管理</li>
                <li class="active">平台用户管理</li>
            </ul>
        </div>
        <div class="page-content" style="padding:8px 15px 24px!important;">
            <c:if test="${error!=null && error!=''}">
                <div class="form-group col-sm-9 center-block text-center">
                    <label class="control-label"><font color='red'>${error}</font></label>
                </div>
            </c:if>
            <div class="b-search-box b-panel" style="margin-bottom:10px;position:relative;">
                <form id="schForm" method="post" action="/crm/web/admin/listUserWithJson">
                    <input type="hidden" name="pagenow" value="<%=cPage%>" id="pagenow">
                    <input type="hidden" name="pagesize" value="15"/>
                    <div class="b-form-group">
                        <input type="text" class="b-form-control" placeholder="登录名" type="text" name="sch_username">
                    </div>
                    <div class="b-form-group operator">
                        <input class="b-form-control" placeholder="真实姓名" type="text" name="sch_truename"></input>
                    </div>
                    <div class="b-form-group">
                        <button type="button" id="schBtn" class="btn btn-info b-search-btn" style="line-height:1.5">搜索
                        </button>
                        <button type="button" data-toggle="modal" data-target="#addUser" style="line-height:1.5"
                                class="btn btn-success b-search-btn">添加用户
                        </button>
                        <a href="javascript:void(0)" class="b-clear-search">清空搜索</a>
                    </div>
                </form>
            </div>
            <table class="table-bordered table-striped table-hover table-condensed cf"
                   style="width:100%;text-align:center;background:#fff;">
                <thead class="cf">
                <tr style="background:#fff;">
                    <th style="text-align: center;height:50px;">序号</th>
                    <th style="text-align: center">姓名</th>
                    <th style="text-align: center">登录名</th>
                    <th style="text-align: center">拥有角色</th>
                    <th style="text-align: center">状态</th>
                    <th style="text-align: center">创建时间</th>
                    <th style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody id="userlist">
                <c:forEach var="user" items="${userList }" varStatus="i">
                    <tr>
                        <td style="text-align: center">${i.index+startNo+1 }</td>
                        <td style="text-align: center"><a href="#"
                                                          onclick="showContent('${user.id}')">${user.truename }</a></td>
                        <td style="text-align: center">${user.username }</td>
                        <td style="text-align: left">${user.roleNames }</td>
                        <td style="text-align: center">
                            <c:if test="${user.state=='正常'}"><span class="label label-success">正常</span></c:if>
                            <c:if test="${user.state=='停用'}"><span class="label label-important">停用</span></c:if>
                            <c:if test="${user.state=='废弃'}"><span class="label label-warning">废弃</span></c:if>
                        </td>
                        <td style="text-align: center"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
                                                                       value="${user.createTime }" type="both"/></td>
                        <td style="text-align: center">
                            <c:if test="${user.state=='停用'}">
                                <button class="btn btn-xs btn-success startUser" data="${user.id}"><i
                                        class="icon-wrench bigger-120"></i>启用
                                </button>
                            </c:if>
                            <c:if test="${user.state=='正常'}">
                                <button class="btn btn-xs btn-danger" name="deleteUser"><i
                                        class="icon-trash bigger-120"></i>停用
                                </button>
                            </c:if>
                            <button class="btn btn-xs btn-info" data-toggle="modal" data-target="#updUser"
                                    name="updateUserButton"><i class="icon-edit bigger-120"></i>编辑
                            </button>
                            <input type="hidden" value="${user.id }" name="uid"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="row b-panel" style="margin: 5px 0px;">
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                    <div id="table_info" class="dataTables_info">第${startNo+1}条到第${startNo+num}条记录，总共${page.totalCount}条记录</div>
                </div>
                <!-- start pagination -->
                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 dataTables_paginate paging_bootstrap pagination"
                     style="margin-top: 10px;">
                    <ul class="dataTables_paginate  pagination" id="pagination">
                        <c:choose>
                            <c:when test="${page.pageNo>1}">
                                <li><a onclick="reloadData(${page.pageNo-1})" href="#" aria-label="Previous"><span
                                        aria-hidden="true">&laquo;</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" style="CURSOR: not-allowed"
                                       aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
                            </c:otherwise>
                        </c:choose>
                        <%

                            for (int i = startPage; i <= endPage; i++) {
                                if (i == cPage) {
                        %>
                        <li class="active"><a href="javascript:void(0);" style="CURSOR: not-allowed"><%=i%>
                        </a></li>
                        <%} else {%>
                        <li><a href="#" onclick="reloadData(<%=i%>)"><%=i%>
                        </a></li>
                        <%
                                }
                            }
                        %>
                        <%if (cPage < maxPage) {%>
                        <!-- <li><span>...</span></li> -->
                        <li><a onclick="reloadData(<%=cPage + 1%>)" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
                        </li>
                        <%} else { %>
                        <li><a href="javascript:void(0);" style="CURSOR: not-allowed" aria-label="Next"><span
                                aria-hidden="true">&raquo;</span></a></li>
                        <%}%>
                    </ul>
                </div>
                <!-- end pagination -->
            </div>
            <!-- Modal for add user -->
            <div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">添加账号</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" action="/crm/web/admin/addUser" id="addUserForm" method="post"
                                  enctype="multipart/form-data">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>真实姓名</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="truename"
                                               placeholder="输入用户的真实姓名" required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>登录名</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="username"
                                               placeholder="输入登录系统的登录名" required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>登录密码</label>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control easyui-validatebox" name="pwd"
                                               placeholder="输入登录密码" required="true" id="pwd">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>再次输入登录密码</label>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control easyui-validatebox" name="repwd"
                                               required="true" id="repwd">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色选择</label>
                                    <div class="col-sm-10">
                                        <c:forEach var="role" items="${roleList }" varStatus="i">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="roleIds"
                                                       value=${role.id }> ${role.roleName }
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">上传头像</label>
                                    <div class="col-sm-5">
                                        <div class="fileupload fileupload-new" data-provides="fileupload">
                                            <div class="fileupload-new thumbnail" style="width: 200px; height: 200px;">
                                                <img src="" alt=""/>
                                            </div>
                                            <div class="fileupload-preview fileupload-exists thumbnail"
                                                 style="max-width: 200px; max-height: 200px; line-height: 20px;"></div>
                                            <div>
					                                    <span class="btn btn-file"><span class="fileupload-new blue"><i
                                                                class="fa fa-folder-open"></i>&nbsp;选择图片</span>
					                                        <span class="fileupload-exists"><i
                                                                    class="fa fa-folder-open blue"></i>&nbsp;更改</span>
					                                        <input type="file" class="default"
                                                                   name="portraitImg"/></span>
                                                <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><i
                                                        class="fa fa-trash-o"></i>&nbsp;删除</a>
                                            </div>
                                        </div>
                                        <span class="label label-important" style="font-size:12px">注意!</span>
                                        <span> 图片预览只支持最新版的 Firefox, Chrome, Opera, Safari 和 Internet Explorer 10
					                            </span>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-sm btn-primary" id="saveSubmit"><i
                                    class="fa fa-save"></i>&nbsp;保存
                            </button>
                            <button type="button" class="btn btn-sm default" data-dismiss="modal"><i
                                    class="fa fa-ban"></i>&nbsp;取消
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal for update user -->
            <div class="modal fade" id="updUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">修改账号</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" action="/crm/web/admin/updUser" id="updUserForm" method="post"
                                  enctype="multipart/form-data">
                                <input type="hidden" name="id">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>真实姓名</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="truename"
                                               placeholder="输入用户的真实姓名" required="true" id="truename">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">登录名</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="username"
                                               placeholder="输入登录系统的登录名" required="true" readonly id="username">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">登录密码</label>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control easyui-validatebox" name="pwd"
                                               placeholder="输入登录密码，如果为空表示不修改原有密码" id="password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">再次输入登录密码</label>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control easyui-validatebox" name="repwd"
                                               id="repassword">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">账号状态</label>
                                    <div class="col-sm-5">
                                        <select id="state" name="state" class="form-control">
                                            <option value="正常">正常</option>
                                            <option value="停用">停用</option>
                                            <option value="废弃">废弃</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色选择</label>
                                    <div class="col-sm-10" id="udpRoles">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">上传头像</label>
                                    <div class="col-sm-5">
                                        <div class="fileupload fileupload-new" data-provides="fileupload">
                                            <div class="fileupload-new thumbnail" style="width: 200px; height: 200px;">
                                                <img src="" alt="" id="preview"/>
                                            </div>
                                            <div class="fileupload-preview fileupload-exists thumbnail"
                                                 style="max-width: 200px; max-height: 200px; line-height: 20px;">

                                            </div>
                                            <div>
					                                    <span class="btn btn-file"><span class="fileupload-new blue"><i
                                                                class="fa fa-folder-open"></i>&nbsp;选择图片</span>
					                                        <span class="fileupload-exists"><i
                                                                    class="fa fa-folder-open blue"></i>&nbsp;更改</span>
					                                        <input type="file" class="default"
                                                                   name="portraitImg"/></span>
                                                <a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><i
                                                        class="fa fa-trash-o"></i>&nbsp;删除</a>
                                            </div>
                                        </div>
                                        <span class="label label-important" style="font-size:12px">注意!</span>
                                        <span> 图片预览只支持最新版的 Firefox, Chrome, Opera, Safari 和 Internet Explorer 10
					                            </span>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-sm btn-primary" id="updSubmit"><i
                                    class="fa fa-save"></i>&nbsp;保存
                            </button>
                            <button type="button" class="btn btn-sm default" data-dismiss="modal"><i
                                    class="fa fa-ban"></i>&nbsp;取消
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal for view user -->
            <div class="modal fade" id="viewUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">账号信息</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">真实姓名</label>
                                    <div class="col-sm-5" id="vtruename">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">登录名</label>
                                    <div class="col-sm-5" id="vusername">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">账号状态</label>
                                    <div class="col-sm-5" id="vstate">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">角色选择</label>
                                    <div class="col-sm-5" id="vRoles">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">上传头像</label>
                                    <div class="col-sm-5">
                                        <div class="fileupload-new thumbnail" style="width: 200px; height: 200px;">
                                            <img src="" id="vpreview"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn default" data-dismiss="modal"><i class="fa fa-ban"></i>&nbsp;关闭
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <form action="/crm/web/admin/changeUserState" id="delForm" method="post">
                <input type="hidden" id="uid" name="uid"/>
                <input type="hidden" name="type" value="0"/>
            </form>
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<form action="/crm/web/admin/deleteUser" id="delForm" method="post">
    <input type="hidden" id="uid" name="uid"/>
</form>

<!-- hidden param -->
<input type="hidden" id="userId" value="${userId}"/>
<input type="hidden" id="uType" value="${uType}"/>

<script src="/fork/assets/scripts/typeahead-bs2.min.js"></script>
<script src='/fork/assets/scripts/jquery-2.0.3.min.js'></script>
<script type="text/javascript" src="/fork/assets/scripts/common.js?201706121051"></script>
<script src="/fork/assets/scripts/jquery-ui-1.10.3.custom.min.js"></script>
<script src="/fork/assets/scripts/jquery.ui.touch-punch.min.js"></script>
<script src="/fork/assets/scripts/jquery.slimscroll.min.js"></script>
<script src="/fork/assets/scripts/jquery.easy-pie-chart.min.js"></script>
<script src="/fork/assets/scripts/jquery.sparkline.min.js"></script>
<script src="/fork/assets/scripts/flot/jquery.flot.min.js"></script>
<script src="/fork/assets/scripts/flot/jquery.flot.pie.min.js"></script>
<script src="/fork/assets/scripts/flot/jquery.flot.resize.min.js"></script>
<script src="/fork/assets/scripts/jquery-ui-1.10.3.full.min.js"></script>
<script src="/fork/assets/scripts/bootbox.min.js"></script>
<script src="/fork/assets/scripts/jquery.ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/ace-elements.min.js"></script>
<script src="/fork/assets/scripts/ace.min.js"></script>
<script src="/fork/assets/scripts/bootstrap-treeview.js"></script>
<script type="text/javascript" src="/fork/assets/scripts/sweetalert.min.js"></script>
<script src="/fork/assets/plugins/easyui/easyloader.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.json.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/fork/assets/plugins/select2/select2.min.js"></script>
<script src="/fork/assets/scripts/loading.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/app.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/admin/user_list.js?201705271109" type="text/javascript"></script>
</body>
</html>