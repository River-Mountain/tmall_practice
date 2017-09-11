package tmall.bean;

import java.util.List;

/**
 * Created by mountain on 2017/8/31.
 * 根据数据库的表设计关系来初步设计实体类
 */
public class Category {
    private String name;
    private int id;
    List<Product> products;
    List<List<Product>> productsByRow;

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

    @Override
    public String toString() {
        return "Category [name=" + name + "]";
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }
    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

}
