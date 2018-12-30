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
        <h2 class="col-6">班级列表</h2>
        <div class="col-6">
            <a href="/class_add" class="btn btn-primary float-right">录入新班级信息</a>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-sm">
                <thead>
                <tr>
                    <th>#</th>
                    <th>班级号</th>
                    <th>班级名</th>
                    <th>班级人数</th>
                    <th>操作</th>
                </tr>
                <tbody>
                <#list classes as class>
                    <tr>
                        <td>${class_index+1}</td>
                        <td>${class.classCode!}</td>
                        <td>${class.className!}</td>
                        <td>${class.number!}</td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="/pick_order?classCode=${class.classCode!}">查看取书记录</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>