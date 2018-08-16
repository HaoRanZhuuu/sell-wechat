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
                <#--表格-->
                    <div class="col-md-12 column">
                        <table class="table table-hover table-striped table-condensed">
                            <thead>
                            <tr>
                                <th>商品ID</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list productInfoPage.content as productInfo>
                    <tr>
                        <td>${productInfo.productId}</td>
                        <td>${productInfo.productName}</td>
                        <td><img height="100" width="100" src="${productInfo.productIcon}"></td>
                        <td>${productInfo.productPrice}</td>
                        <td>${productInfo.productStock}</td>
                        <td>${productInfo.productDescription}</td>
                        <td>${productInfo.categoryType}</td>
                        <td>${productInfo.createTime}</td>
                        <td>${productInfo.updateTime}</td>
                        <td>
                            <a class="btn btn-info btn-sm" href="/sell/seller/product/index?productId=${productInfo.productId}" role="button">修改</a>
                        </td>
                        <td>
                            <#if productInfo.getProductStatusEnum().code == 0>
                                <a class="btn btn-danger btn-sm" href="/sell/seller/product/cancel?productId=${productInfo.productId}" role="button">下架</a>
                            <#--<#else>-->
                                    <#--<button type="button" class="btn btn-danger btn-sm" disabled="disabled">取消</button>-->
                            <#--</#if>-->
                        </td>
                    </tr>
                    </#list>
                            </tbody>
                        </table>
                    </div>
                <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#--上一页-->
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                        </#if>
                        <#--页码遍历    -->
                        <#list 1..productInfoPage.totalPages as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#--下一页-->
                        <#if currentPage gte productInfoPage.totalPages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/product/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                        </#if>

                        </ul>
                    </div>
                </div>
            </div>
    </div>
    </div>
    </body>
</html>

