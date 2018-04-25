<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>CRM</title>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->
    <link rel="icon" href="/fork/assets/img/favicon.ico"/>
    <link href="/fork/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/fork/assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="/fork/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->

    <link rel="stylesheet" href="/fork/assets/css/ace-fonts.css"/>
    <link rel="stylesheet" href="/fork/assets/css/crm.css?201705271109"/>

    <!-- ace styles -->

    <link rel="stylesheet" href="/fork/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="/fork/assets/css/ace-skins.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/fork/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <link rel="stylesheet" href="/fork/assets/css/jquery-ui-1.10.3.full.min.css">
    <link rel="stylesheet" href="/fork/assets/css/message/index.css"/>
    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <script src="/fork/assets/scripts/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="/fork/assets/scripts/html5shiv.js"></script>
    <script src="/fork/assets/scripts/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="/crm/web/front/addGroupPage" class="navbar-brand">
                <small>
                    <!-- <i class="icon-leaf"></i> -->
                    百程 CRM
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo userPortrait" src=""
                             style="height:45px; width:45px;max-width:45px;"/>
                        <span class="user-info">
									<h5 id="userName"></h5>
								</span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li class="divider"></li>
                        <li>
                            <a href="/crm/web/user/logout">
                                <i class="icon-off"></i>
                                退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>
                    <span class="btn btn-info"></span>
                    <span class="btn btn-warning"></span>
                    <span class="btn btn-danger"></span>
                </div>
            </div><!-- #sidebar-shortcuts -->

            <ul class="nav nav-list">
                <%--系统管理--%>
                <shiro:hasPermission name="crm:system">
                    <li id="one_level_system">
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-book"></i>
                            <span class="menu-text"> 系统管理 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu" style="display:none">
                            <shiro:hasPermission name="system:user">
                                <li id="two_level_user">
                                    <a href="javascript:void(0)" class="user_manage">
                                        <i class="icon-double-angle-right"></i>
                                        用户管理
                                    </a>
                                </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="system:role">
                                <li id="two_level_role">
                                    <a href="javascript:void(0)" class="role_manage">
                                        <i class="icon-double-angle-right"></i>
                                        角色管理
                                    </a>
                                </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="system:permit">
                                <li id="two_level_permit">
                                    <a href="javascript:void(0)" class="permit_manage">
                                        <i class="icon-double-angle-right"></i>
                                        权限管理
                                    </a>
                                </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasPermission>
                <%--个人中心--%>
                <shiro:hasPermission name="crm:persional">
                    <li id="one_level_persional">
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-book"></i>
                            <span class="menu-text"> 个人中心 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu" style="display:none;">
                            <shiro:hasPermission name="persional:pwd">
                                <li id="two_level_password">
                                    <a href="javascript:void(0)" class="person_center">
                                        <i class="icon-double-angle-right"></i>
                                        密码修改
                                    </a>
                                </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                </shiro:hasPermission>
            </ul><!-- /.nav-list -->

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content" style="background: #f3f3f3;">
            <iframe width="100%" id="mainContent" src="/crm/web/front/addGroupPage"
                    frameborder="0"></iframe>
        </div><!-- /.main-content -->

    </div><!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='/fork/assets/scripts/jquery-2.0.3.min.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='/fork/assets/scripts/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='/fork/assets/scripts/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>
<script src="/fork/assets/scripts/bootstrap.min.js"></script>
<script src="/fork/assets/scripts/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="/fork/assets/scripts/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="/fork/assets/scripts/common.js?201706121051"></script>
<script src="/fork/assets/scripts/jquery-ui-1.10.3.custom.min.js"></script>
<script src="/fork/assets/scripts/jquery.ui.touch-punch.min.js"></script>
<script src="/fork/assets/scripts/jquery.slimscroll.min.js"></script>
<script src="/fork/assets/scripts/jquery.easy-pie-chart.min.js"></script>
<script src="/fork/assets/scripts/jquery.sparkline.min.js"></script>
<script src="/fork/assets/scripts/flot/jquery.flot.min.js"></script>
<script src="/fork/assets/scripts/flot/jquery.flot.pie.min.js"></script>
<script src="/fork/assets/scripts/flot/jquery.flot.resize.min.js"></script>
<script src="/fork/assets/scripts/jquery.ui.datepicker-zh-CN.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/jquery-ui-1.10.3.full.min.js"></script>
<!-- ace scripts -->
<script src="/fork/assets/scripts/ace-elements.min.js"></script>
<script src="/fork/assets/scripts/ace.min.js"></script>

<script type="text/javascript" src="/fork/assets/scripts/layout/layout.js"></script>
<!-- inline scripts related to this page -->
<!--<script src="../fork/assets/js/vue.js"></script>-->
<script>
    $(function () {
        $("#mainContent").css("height", document.body.scrollHeight - 50);
        $("#mainContent").css("height", window.innerHeight - 50);
        //alert(document.body.scrollHeight)
        //document.getElementById("mainContent").style.width = document.body.scrollWidth;
    });
</script>
</body>
</html>
