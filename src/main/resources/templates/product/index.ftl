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
                <form role="form" method="post" action="/sell/seller/product/save">
                <div class="col-md-1 column"></div>
                <div class="col-md-3 column">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!""}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? &&productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                        >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="text" name="productId" value="${(productInfo.productId)!""}" hidden>
                        <button type="submit" class="btn btn-default">提交</button>
                </div>
                <div class="col-md-4 column">
                    <div class="form-group">
                        <label>图片</label>
                        <br>
                        <img height="200" width="200" src="${(productInfo.productIcon)!""}" alt="">
                        <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!""}"/>
                    </div>
                </div>
                <div class="col-md-4 column"></div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

