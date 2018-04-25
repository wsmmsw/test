<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>用户登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="/fork/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="/fork/assets/plugins/select2/select2_metro.css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="/fork/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="/fork/assets/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <link href="/fork/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="icon" href="/fork/assets/img/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo" style="font-size: 36px; color: #fff; font-weight: bolder;">
    百程CRM系统
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="web/user/login" method="post" style="padding: 0 20px;">
        <h3 class="form-title">用户登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span id="errormsg">
                        <c:if test="${error!=null && error!=''} ">${error }</c:if>
                    </span>
        </div>

        <div class="form-group" style="display:none">
            <label class="control-label visible-ie8 visible-ie9">登录身份</label>
            <div class="input-icon">
                <i class="fa fa-group"></i>
                <select name="uType" class="select2 form-control" id="identity_select2">
                    <!-- <option value="1">商户</option> -->
                    <option value="0">平台运维人员</option>
                </select>
            </div>
        </div>

        <c:if test="${uType!=null && uType!='' }">
            <c:if test="${uType=='admin' }">
                <input type="hidden" name="uType" value="0"/>
            </c:if>
            <c:if test="${uType=='biz' }">
                <input type="hidden" name="uType" value="1"/>
            </c:if>
        </c:if>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input name="username" id="username" size="25" style="width:100%"
                       class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input name="pwd" id="password" size="25" class="form-control placeholder-no-fix" type="password"
                       autocomplete="off" placeholder="密码"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input type="text" id="rand" name="rand" style="width:100%" class="form-control" autocomplete="off"
                       placeholder="请输入验证码">
                <img id="randImage" src="RandomImageServlet" onclick="refreshImg()"
                     style="border:0;vertical-align:bottom;cursor:pointer;"/>
                <a href="javascript:refreshImg()" id="change">&nbsp;<span>
                                <font color="#CE8C13">看不清楚，换一张</font></span>
                </a>
            </div>
        </div>
        <div class="form-actions">
            <button id="loginBut" type="submit" class="btn blue pull-right"> 登录</button>
        </div>
    </form>
    <!-- END LOGIN FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="/fork/assets/plugins/respond.min.js"></script>
<script src="/fork/assets/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="/fork/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="/fork/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/fork/assets/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/fork/assets/scripts/app.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/login-soft.js" type="text/javascript"></script>
<script src="app/lib/security/sha256.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        App.init();
        Login.init();
    });

    function refreshImg() {
        $("#randImage").attr("src", "RandomImageServlet?" + Math.random());
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>