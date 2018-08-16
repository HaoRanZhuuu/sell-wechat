<html>
<#include "../common/header.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <h4>
                    成功!
                </h4> <strong>${message}</strong><a href="${url}" class="alert-link">没有自动跳转，请点击</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    setTimeout('location.href="${url}"',2500);
</script>
</html>
