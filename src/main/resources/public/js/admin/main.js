(function($){

    // 登出
    function logout() {
        if(!confirm("确定要退出么?")) {
            return;
        }

        $("<form class='hidden' action='/admin/logout' method='post'></form>").submit();
    }

    // 分类栏目 折叠/展开
    function categoryFolding() {
        var $this = $(this);
        var $leftSide = $(".left-side");

        // 设置分类栏的选中状态
        var $leftSidePanels = $leftSide.find(".panel");
        var $currentPanel = $this.parent();

        $leftSidePanels.addClass("panel-default").removeClass("panel-primary");
        $currentPanel.removeClass("panel-default").addClass("panel-primary");

        // 设置分类的折叠/展开
        var $leftSidePanelBody = $leftSide.find(".panel-body");
        var $currentPanelBody = $this.parent().find(".panel-body");

        $leftSidePanelBody.addClass("panel-body-hidden");
        $currentPanelBody.removeClass("panel-body-hidden");
    }

    // iframe高度设定
    function iframeAutoHeight(){
        var $iframe = $(this);
        var contentDocument = this.contentDocument || this.Document;

        $iframe.height(contentDocument.body.offsetHeight || contentDocument.body.scrollHeight);
    }

    $(document).ready(function(){

        // 登出
        $("#logout_action").click(logout);

        // 分类栏目 折叠/展开
        $(".panel-heading").click(categoryFolding);

        // iframe高度设定
        $("iframe").on("load", iframeAutoHeight);
    });

})(jQuery);