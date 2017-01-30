(function($){

    // 选择一级分类
    function selectFirstLevelCategory() {
        var $this = $(this);
        var id = $this.data("id");

        location.href = "/admin/categories/" + id + "/select";
    }

    function bindEvent() {
        $(".first-category-section").on('click', '.first-level-category-row', selectFirstLevelCategory);
    }

    bindEvent();
})(jQuery);