<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@page import="com.baicheng.fork.core.feature.orm.mybatis.Page" %>
<%@page import="com.baicheng.fork.domain.user.Role" %>
<%
    Page<Role> nowPage = (Page<Role>) request.getAttribute("page");
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
    <link rel="stylesheet" href="/fork/assets/plugins/data-tables/DT_bootstrap.css">
    <link href="/fork/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/fork/assets/plugins/select2/select2_metro.css"/>
    <link href="/fork/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/pages/tasks.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="/fork/assets/css/custom.css" rel="stylesheet" type="text/css"/>

    <link href="/fork/assets/css/sweetalert.css" rel="stylesheet"/>
    <link href="/fork/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/fork/assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/jquery-ui-1.10.3.full.min.css"/><!--日历、弹层-->
    <link rel="stylesheet" href="/fork/assets/css/crm.css"/>
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
                <li class="active">角色管理</li>
            </ul><!-- .breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <c:if test="${error!=null && error!=''}">
                    <div class="form-group col-sm-9 center-block text-center">
                        <label for="inputEmail3" class="control-label"><font color='red'>${error}</font></label>
                    </div>
                </c:if>
                <div class="b-search-box b-panel" style="margin-bottom:10px;position:relative;">
                    <form id="schForm" method="post" action="/crm/web/admin/listRoleWithJson?type=1">
                        <input type="hidden" name="pagenow" value="<%=cPage%>" id="pagenow">
                        <input type="hidden" name="pagesize" value="15"/>
                        <div class="b-form-group">
                            <input type="text" class="b-form-control" placeholder="角色名" type="text" name="sch_roleName">
                        </div>
                        <div class="b-form-group">
                            <button type="button" id="schBtn" style="line-height:1.5" class="btn btn-info b-search-btn">
                                搜索
                            </button>
                            <button style="line-height:1.5" type="button" data-toggle="modal" data-target="#addRole"
                                    class="btn btn-success b-search-btn">添加角色
                            </button>
                            <a href="javascript:void(0)" class="b-clear-search">清空搜索</a>
                        </div>
                    </form>
                </div>
                <table class="table-bordered table-striped table-hover table-condensed cf"
                       style="text-align:center;background:#fff;">
                    <thead class="cf">
                    <tr style="background:#fff;">
                        <th style="text-align: center;height:50px;">序号</th>
                        <th style="text-align: center">角色名</th>
                        <th style="text-align: center">角色标记</th>
                        <th style="text-align: center">角色说明</th>
                        <th style="text-align: center">拥有权限</th>
                        <th style="text-align: center">创建时间</th>
                        <th style="text-align: center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="rolelist">
                    <c:forEach var="role" items="${roleList }" varStatus="i">
                        <tr>
                            <td style="text-align: center">${i.index+startNo+1 }</td>
                            <td style="text-align: center">${role.roleName }</td>
                            <td style="text-align: center">${role.roleSign }</td>
                            <td style="text-align: left">${role.description }</td>
                            <td style="text-align: left">${role.permissions }</td>
                            <td style="text-align: center"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss"
                                                                           value="${role.cdate }"
                                                                           type="both"></fmt:formatDate></td>
                            <td style="text-align: center">
                                <button class="btn btn-xs btn-info" data-toggle="modal" data-target="#updRole"
                                        name="updateRoleButton"><i class="icon-edit bigger-120"></i> 编辑
                                </button>
                                <input type="hidden" value="${role.id }" name="rid"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row b-panel" style="margin: 10px -1px;">
                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                        <div id="table_info" class="dataTables_info">
                            第${startNo+1}条到第${startNo+num}条记录，总共${page.totalCount}条记录
                        </div>
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
                                    <li><a href="javascript:void(0);" style="CURSOR: not-allowed" aria-label="Previous"><span
                                            aria-hidden="true">&laquo;</span></a></li>
                                </c:otherwise>
                            </c:choose>
                            <%

                                for (int i = startPage; i <= endPage; i++) {
                                    if (i == cPage) {
                            %>
                            <li class="active"><a href="javascript:void(0);" style="CURSOR: not-allowed"><%=i%>
                            </a></li>
                            <%} else {%>
                            <li><a onclick="reloadData(<%=i%>)" href="#"><%=i%>
                            </a></li>
                            <%
                                    }
                                }
                            %>
                            <%if (cPage < maxPage) {%>
                            <!-- <li><span>...</span></li> -->
                            <li><a onclick="reloadData(<%=cPage + 1%>)" href="#" aria-label="Next"><span
                                    aria-hidden="true">&raquo;</span></a></li>
                            <%} else { %>
                            <li><a href="javascript:void(0);" style="CURSOR: not-allowed" aria-label="Next"><span
                                    aria-hidden="true">&raquo;</span></a></li>
                            <%}%>
                        </ul>
                    </div>
                    <!-- end pagination -->
                </div>
            </div>
            <!-- Modal for add user -->
            <div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">添加角色</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" action="/crm/web/admin/addRole" id="addRoleForm"
                                  method="post">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>角色名</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="roleName"
                                               placeholder="输入角色名" required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>角色标记</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="roleSign"
                                               placeholder="输入角色标记" required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">角色说明</label>
                                    <div class="col-sm-5">
                                        <textarea class="form-control " name="description" rows="5"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">权限选择</label>
                                    <div class="col-sm-10">
                                        <c:forEach var="permission" items="${permissionList }" varStatus="i">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="permissionIds"
                                                       value=${permission.id }> ${permission.permissionName }
                                            </label>
                                        </c:forEach>
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
            <div class="modal fade" id="updRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">修改角色信息</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-horizontal" action="/crm/web/admin/updRole" id="updRoleForm"
                                  method="post">
                                <input type="hidden" name="id">
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>角色名</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="roleName"
                                               placeholder="输入角色名" required="true" id="roleName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label"><font color='red'>*</font>角色标记</label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control easyui-validatebox" name="roleSign"
                                               placeholder="输入角色标记" required="true" id="roleSign">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-4 control-label">角色说明</label>
                                    <div class="col-sm-5">
                                        <textarea class="form-control " name="description" rows="5"
                                                  id="description"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">权限选择</label>
                                    <div class="col-sm-10" id="selectPermission">
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
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<!-- hidden param -->
<input type="hidden" id="userId" value="${userId}"/>
<input type="hidden" id="uType" value="${uType}"/>

<script src="/fork/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/bootstrap.min.js"></script>
<script src="/fork/assets/scripts/typeahead-bs2.min.js"></script>
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

<script src="/fork/assets/plugins/easyui/easyloader.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.json.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
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
<script src="/fork/assets/scripts/ace-extra.min.js"></script>
<script src="/fork/assets/scripts/admin/role_list.js?201705271109" type="text/javascript"></script>
</body>
</html>
