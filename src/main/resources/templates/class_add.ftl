<!DOCTYPE html>
<html lang="en">
<head>
    <title>教材管理系统</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <style>
        th, td {
            white-space: nowrap;
            text-align: center;
        }
    </style>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar bg-dark navbar-expand-md navbar-dark">
    <a class="navbar-brand" href="/textbook">教材管理系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/textbook">教材管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/repository">仓库管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/provider">供应商管理</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/class">班级管理</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="margin-top: 15px">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2>请填写班级信息</h2>
            <form action="/do_class_add" method="post">
                <div class="form-group">
                    <label>班级名称:</label>
                    <input type="text" class="form-control" name="className" autocomplete="off"
                           placeholder="请输入班级名称" required>
                </div>
                <div class="form-group">
                    <label>班级人数:</label>
                    <input type="number" class="form-control" name="number" autocomplete="off"
                           placeholder="请输入班级人数" required>
                </div>
                <input type="submit" class="form-control btn btn-primary" value="完成录入">
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>