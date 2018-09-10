<html>
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--侧边栏 -->
           <#include "../common/nav.ftl">
<#--主表-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">

                <div class="col-md-1 column"></div>
                <div class="col-md-3 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" type="text" class="form-control" value="${(productCategory.categoryType)!""}"/>
                        </div>
                        <input type="text" name="categoryId" value="${(productCategory.categoryId)!""}" hidden>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
                <div class="col-md-4 column"></div>
                <div class="col-md-4 column"></div>

            </div>
        </div>
    </div>
</div>
</body>
</html>

