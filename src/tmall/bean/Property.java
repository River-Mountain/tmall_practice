package tmall.bean;

/**
 * Created by mountain on 2017/8/31.
 * 与Category的多对一关系
 */
public class Property {
    private String name;
    private Category category;
    private int id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
//    和表结构好像不太一致？？
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
