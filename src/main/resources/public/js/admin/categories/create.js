(function($){

    // 取消，返回上一级页面
    function cancel() {
        var $this = $(this);
        var url = $this.data("url");

        location.href = url;
    }

    // 绑定事件
    function bindEvent() {
        $("#cancel").click(cancel);
    }

    bindEvent();
})(jQuery);