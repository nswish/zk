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

    // Notify
    function notify() {
        $.notify.apply(null, arguments);
    }

    // 选择分类
    function categorySelected() {
        var $this = $(this);
        var url = $this.data('url');

        $("*[data-selected]").removeAttr("data-selected");
        $this.attr("data-selected", true);

        setMainContentUrl(url);
    }

    // 设定主内容区的标题
    function setMainContentTitle() {
        var $leftSide = $(".left-side");
        var $selectedCategory = $leftSide.find("*[data-selected=true]");

        var secondCategoryTitle = $selectedCategory.attr('title');
        var firstCategoryTitle = $selectedCategory.parents(".panel").find(".panel-heading").attr('title');

        var contentDocument = this.contentDocument || this.Document;
        var thirdCategoryTitle = $(contentDocument).find('head title').text();

        $(".main-content-title").text(_.compact([firstCategoryTitle, secondCategoryTitle, thirdCategoryTitle]).join(" > "));
    }

    // 设定主内容区载入的页面
    function setMainContentUrl(url) {
        var $iframe = $("iframe");
        $iframe.attr('src', url);
    }

    // 绑定事件
    function bindEvent() {
        var $leftSide = $(".left-side");

        // 登出
        $("#logout_action").click(logout);

        // 分类栏目 折叠/展开
        $leftSide.on('click', '.panel-heading', categoryFolding);

        // iframe高度设定
        $("iframe").on("load", iframeAutoHeight).on("load", setMainContentTitle);

        // 选择分类
        $leftSide.on('click', '.category li', categorySelected);
    }

    bindEvent();

    // 输出$notify到全局作用域,可供iframe子页面调用
    _.extend(window, {
        "$notify": notify
    });
})(jQuery);