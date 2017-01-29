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

    // 删除用户
    function deleteUser() {
        if(!confirm("确定要删除用户么?")) {
            return;
        }

        var $this = $(this);
        var id = $this.data("id");
        var $form = $("<form action='/admin/users/" + id + "/delete' method='post'></form>")

        $form.submit();
    }

    // 绑定事件
    function bindEvent() {
        $("#add_user").click(jumpToCreateUser);

        var $userTable = $(".user-table");
        $userTable.on('click', "*[data-action=edit]", jumpToEditUser);
        $userTable.on('click', "*[data-action=delete]", deleteUser);
    }

    bindEvent();
})(jQuery);