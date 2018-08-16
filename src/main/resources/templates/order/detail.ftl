<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--侧边栏 -->
           <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <#--订单数据-->
                <div class="col-md-12 column">
                    <table class="table table-hover table-condensed table-striped">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <#--订单详情数据-->
                <div class="col-md-12 column">
                    <table class="table table-hover table-condensed table-striped">
                        <thead>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTO.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                    <div class="col-md-10 column"></div>
                    <div class="col-md-2 column">
                        <#if orderDTO.getOrderStatusEnum().code == 0 >
                            <a type="button" class="btn btn-success" href="/sell/seller/order/finish?orderId=${orderDTO.orderId}">完成订单</a>
                            <a type="button" class="btn btn-danger" href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消订单</a>
                        </#if>

                    </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>