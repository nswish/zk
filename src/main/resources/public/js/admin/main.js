$(document).ready(function(){

    // 登出
    $("#logout_action").click(function(){
        if(!confirm("确定要退出么?")) {
            return;
        }

        var $form = $("<form class='hidden' action='/admin/logout' method='post'></form>");
        $form.appendTo(document.body).submit();
    });

    // 分类栏目 折叠/展开
    $(".panel-heading").click(function(){
        var $this = $(this);

        $(".panel-heading").parent().addClass("panel-default").removeClass("panel-primary");
        $(".panel-body").addClass("panel-body-hidden");

        $this.parent().removeClass("panel-default").addClass("panel-primary");
        var $panelBody = $this.parent().find(".panel-body");
        $panelBody.removeClass("panel-body-hidden");
    });
});