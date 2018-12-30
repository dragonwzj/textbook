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
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <h2>请填写购书订单信息</h2>
            <form action="/do_buy_store" method="post">
                <div class="form-group">
                    <label>供应商名称:</label>
                    <input type="text" class="form-control" name="providerName" autocomplete="off"
                           value="${provider.providerName}" required readonly>
                    <input type="text" class="form-control" name="providerCode" autocomplete="off"
                           value="${provider.providerCode}" required readonly hidden>
                </div>
                <div class="form-group">
                    <label>教材:</label>
                    <select class="form-control" name="textbookCode">
                        <#list books as book>
                            <option value="${book.textbookCode}">${book.textbookName}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>请选择要用于存放的仓库:</label>
                    <select class="form-control" name="repositoryCode">
                        <#list repositories as repository>
                            <option value="${repository.repositoryCode}">${repository.repositoryName}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label>数量:</label>
                    <input type="number" class="form-control" name="num" autocomplete="off"
                           placeholder="请输入购买数量" required>
                </div>
                <input type="submit" class="form-control btn btn-primary" value="完成录入">
            </form>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>