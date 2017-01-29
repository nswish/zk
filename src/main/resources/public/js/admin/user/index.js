(function($){

    // 新增按钮跳转
    function jumpToCreateUser() {
        location.href = "/admin/users/create";
    }

    // 编辑按钮跳转
    function jumpToEditUser() {
        var $this = $(this);
        location.href = "/admin/users/" + $this.data("id") + "/edit";
    }

    // 绑定事件
    function bindEvent() {
        $("#add_user").click(jumpToCreateUser);

        $(".user-table").on('click', "*[data-action=edit]", jumpToEditUser);
    }

    bindEvent();
})(jQuery);