<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>百程 CRM</title>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

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
    <link href="/fork/assets/plugins/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css">
    <link href="/fork/assets/plugins/easyui/themes/icon.css" rel="stylesheet" type="text/css">
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
                    <a href="../member_system/customer_addgroup.html">首页</a>
                </li>
                <li class="active">个人中心</li>
            </ul><!-- .breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    修改密码</span>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="portlet box">
                    <div style="display: block;" class="portlet-body no-more-tables">
                        <div class="row">
                            <form class="form-horizontal" action="/crm/web/personal/changPwd" id="updForm"
                                  method="post">
                                <div class="form-group" style="height: 35px;">
                                    <label class="col-lg-3 col-md-3 col-sm-6 col-xs-12 control-label"><font color='red'>*</font>旧密码</label>
                                    <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12">
                                        <input type="password" class="form-control easyui-validatebox" name="oldPwd"
                                               placeholder="输入旧密码" required="true">
                                    </div>
                                </div>
                                <div class="form-group" style="height: 35px;">
                                    <label class="col-lg-3 col-md-3 col-sm-6 col-xs-12 control-label"><font color='red'>*</font>新密码</label>
                                    <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12">
                                        <input type="password" class="form-control easyui-validatebox" name="newPwd"
                                               placeholder="输入新密码" required="true">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 col-md-3 col-sm-6 col-xs-12 control-label"><font color='red'>*</font>再次输入新密码</label>
                                    <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12">
                                        <input type="password" class="form-control easyui-validatebox" name="reNewPwd"
                                               placeholder="输入新密码" required="true">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-sm btn-primary" id="saveSubmit"><i
                                    class="fa fa-save"></i>&nbsp;保存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div><!-- /.main-container-inner -->
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<script src='/fork/assets/scripts/jquery-2.0.3.min.js'></script>
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
<script src="/fork/assets/scripts/ace-extra.min.js"></script>
<script src="/fork/assets/plugins/easyui/easyloader.js" type="text/javascript"></script>
</body>
</html>

<script type="text/javascript">
    easyloader.locale = "zh_CN";//表单验证组件支持用中文提示
    $("#saveSubmit").click(function () {
        $('#updForm').submit();
    });
    using(['form', 'messager'], function () {
        $('#updForm').form({
            onSubmit: function () {
                $("#saveSubmit").attr("disabled", true);
                var bb = $(this).form('validate');
                if (!bb) {
                    var pwd = $('#newPwd').val();
                    var repwd = $('#reNewPwd').val();
                    if (pwd != repwd)
                        bb = false;
                    $("#saveSubmit").attr("disabled", false);
                }
                return bb;
            },
            success: function (data) {
                var $data = $.parseJSON(data);
                $("#saveSubmit").attr("disabled", false);
                alert($data.msg);
            }
        });
    });
</script>