<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>短信通道设置</title>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="/fork/assets/img/favicon.ico"/>
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

    <link rel="stylesheet" href="/fork/assets/plugins/data-tables/DT_bootstrap.css">
    <link href="/fork/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css">
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
                <li class="active">短信管理</li>
                <li class="active">短信发送通道管理</li>
            </ul><!-- .breadcrumb -->
        </div>

        <div class="" style="margin-left:0px;background-color:#fff">
            <div style="margin: 100px auto 0; width: 400px; border: 1px solid #bfbfbf; text-align: center;padding: 25px;">
                <form>
                    <c:forEach items="${requestScope.channels}" var="channel">
                        <label style="cursor: pointer;">
                            <input id="${channel.id}" type="radio" name="channel" value="${channel.id}"
                            <c:if test="${channel.isEnabled}">
                                   checked="checked"
                            </c:if>
                            > ${channel.name} </input>&nbsp;&nbsp;&nbsp;&nbsp;
                        </label>
                    </c:forEach>
                    <br/><br/><br/>
                    <shiro:hasPermission name="sms:setChannelButton">
                        <input type="button" class="btn btn-info" value="提交" onclick="sub()"/>
                    </shiro:hasPermission>
                </form>
            </div>
        </div><!-- /.page-content -->
    </div><!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>

<script src='/fork/assets/scripts/jquery-2.0.3.min.js'></script>
<script src="/fork/assets/scripts/bootstrap.min.js"></script>
<script src="/fork/assets/scripts/typeahead-bs2.min.js"></script>
<script type="text/javascript" src="/fork/assets/scripts/common.js?201705271109"></script>
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
<script src="/fork/assets/scripts/ace-extra.min.js"></script>

<script src="/fork/assets/plugins/easyui/easyloader.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap-fileupload/bootstrap-fileupload.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.json.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="/fork/assets/plugins/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/fork/assets/plugins/select2/select2.min.js"></script>
<script src="/fork/assets/scripts/app.js" type="text/javascript"></script>
<script src="/fork/assets/scripts/util/sms-channel.js" type="text/javascript"></script>
</body>
</html>