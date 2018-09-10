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
        <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">
                            提醒
                        </h4>
                    </div>
                    <div class="modal-body">
                        您有新的订单
                    </div>
                    <div class="modal-footer">
                        <button onclick="javascript:document.getElementById('music').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button onclick="location.reload()" type="button" class="btn btn-success">查看订单</button>
                    </div>
                </div>
            </div>
        </div>

    <#--播放提示音-->
    <audio id="music" loop="loop">
        <source src="/sell/mp3/alert.ogg" type="audio/mpeg">
    </audio>
    <script>
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
        } else {
            alert('浏览器不支持wensocket！！')
        }
        websocket.onopen = function (ev) {
            console.log('建立连接');
        }

        websocket.onclose = function (ev) {
            console.log('连接关闭');
        }

        websocket.onmessage = function (ev) {
            console.log('收到消息'+ev.data);
            $('#myModal').modal('show');
            document.getElementById('music').play();
        }

        websocket.onerror = function (ev) {
            alert('wensocket通信错误');
        }

        window.onbeforeunload = function (ev) {
            websocket.close();
        }
    </script>
    </body>
</html>

