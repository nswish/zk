(function($){

    // 新增按钮跳转
    function jumpToCreateUser() {
        location.href = "/admin/user/create";
    }

    $(document).ready(function(){
        $("#add_user").click(jumpToCreateUser);
    });
})(jQuery);