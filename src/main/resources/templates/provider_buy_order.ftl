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
                <a class="nav-link active" href="/provider">供应商管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/class">班级管理</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="margin-top: 15px">
        <h2 class="col-12">从${provider.providerName}购书记录</h2>
        <div class="table-responsive">
            <table class="table table-hover table-sm">
                <thead>
                <tr>
                    <th>#</th>
                    <th>教材名</th>
                    <th>存放仓库名</th>
                    <th>数量</th>
                    <th>时间</th>
                </tr>
                <tbody>
                <#list orders as order>
                    <tr>
                        <td>${order_index+1}</td>
                        <td>${order.textbook.textbookName!}</td>
                        <td>${order.repository.repositoryName!}</td>
                        <td>${order.num!}</td>
                        <td>${order.purchaseTime!}</td>
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