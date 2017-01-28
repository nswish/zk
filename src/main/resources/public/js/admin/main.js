$(document).ready(function(){

    // 登出
    $("#logout_action").click(function(){
        if(!confirm("确定要退出么?")) {
            return;
        }

        var $form = $("<form class='hidden' action='/admin/logout' method='post'></form>");
        $form.appendTo(document.body).submit();
    });
});