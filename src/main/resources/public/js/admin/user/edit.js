(function($){

    // 取消，返回上一级页面
    function cancel() {
        location.href = "/admin/users"
    }

    // 绑定事件
    function bindEvent() {
        $("#cancel").click(cancel);
    }

    bindEvent();
})(jQuery);