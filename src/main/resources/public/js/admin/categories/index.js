(function($){

    // 选择一级分类
    function selectFirstLevelCategory() {
        var $this = $(this);
        var id = $this.data("id");

        location.href = "/admin/categories/" + id + "/select";
    }

    // 新增二级分类
    function addNewSecondLevelCategory() {
        var $this = $(this);
        var parentId = $this.data("id");

        location.href = "/admin/categories/" + parentId + "/create";
    }

    function bindEvent() {
        $(".first-category-section").on('click', '.first-level-category-row', selectFirstLevelCategory);

        $("#add_category").click(addNewSecondLevelCategory);
    }

    bindEvent();
})(jQuery);