package cn.edu.suibe.zk.application.admin.controllers.forms;

public class CategoryForm {
    private String title;
    private int parentId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
