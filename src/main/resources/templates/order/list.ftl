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
                                <th>订单ID</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                    <#list orderDTOPage.content as orderDTO>
                    <tr>
                        <td>${orderDTO.orderId}</td>
                        <td>${orderDTO.buyerName}</td>
                        <td>${orderDTO.buyerPhone}</td>
                        <td>${orderDTO.buyerAddress}</td>
                        <td>${orderDTO.orderAmount}</td>
                        <td>${orderDTO.getOrderStatusEnum().message}</td>
                        <td>${orderDTO.getPayStatusEnum().message}</td>
                        <td>${orderDTO.createTime}</td>
                        <td>
                            <a class="btn btn-info btn-sm" href="/sell/seller/order/detail?orderId=${orderDTO.orderId}" role="button">详情</a>
                        </td>
                        <td>
                            <#if orderDTO.getOrderStatusEnum().code == 0>
                                <a class="btn btn-danger btn-sm" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}" role="button">取消</a>
                            <#else>
                                    <button type="button" class="btn btn-danger btn-sm" disabled="disabled">取消</button>
                            </#if>
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
                            <li><a href="/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                        </#if>
                        <#--页码遍历    -->
                        <#list 1..orderDTOPage.totalPages as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                            </#if>
                        </#list>
                        <#--下一页-->
                        <#if currentPage gte orderDTOPage.totalPages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                        </#if>

                        </ul>
                    </div>
                </div>
            </div>
    </div>
    </div>
    </body>
</html>

