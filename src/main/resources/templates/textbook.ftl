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
                <a class="nav-link active" href="/textbook">教材管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/repository">仓库管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/provider">供应商管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/class">班级管理</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="margin-top: 15px">
        <h2 class="col-6">教材列表</h2>
        <div class="col-6">
            <a href="/textbook_add" class="btn btn-primary float-right">录入新教材信息</a>
        </div>
        <div class="table-responsive">
            <table class="table table-hover table-sm">
                <thead>
                <tr>
                    <th>#</th>
                    <th>教材号</th>
                    <th>教材名</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>操作</th>
                </tr>
                <tbody>
                <#list textbooks as textbook>
                    <tr>
                        <td>${textbook_index+1}</td>
                        <td>${textbook.textbookCode!}</td>
                        <td>${textbook.textbookName!}</td>
                        <td>${textbook.price!}</td>
                        <td>${textbook.num!}</td>
                        <td>
                            <a class="btn btn-success btn-sm" href="/textbook_distribute?textbookCode=${textbook.textbookCode!}">分发教材</a>
                            <a class="btn btn-primary btn-sm" href="/textbook_buy_order?textbookCode=${textbook.textbookCode!}">查看购买记录</a>
                            <a class="btn btn-primary btn-sm" href="/textbook_distribute_order?textbookCode=${textbook.textbookCode!}">查看分发记录</a>
                            <a class="btn btn-primary btn-sm" href="/textbook_distribution?textbookCode=${textbook.textbookCode!}">查看存放分布</a>
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