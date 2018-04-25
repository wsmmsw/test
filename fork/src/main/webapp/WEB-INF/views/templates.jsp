<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="/fork/assets/plugins/bootstrap/css/bootstrap.css"/>
</head>
<body>
<script src="/fork/assets/plugins/jquery/jquery-1.11.1.js" type="text/javascript" charset="utf-8"></script>
<script src="/fork/assets/plugins/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
<script src="" type="text/javascript" charset="utf-8"></script>
</body>
</html>