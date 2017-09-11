package tmall.bean;

/**
 * Created by mountain on 2017/8/31.
 * 根据数据库的表设计关系来初步设计实体类
 */
public class User {
    private String password;
    private String name;
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //    评价匿名用
    public String getAnonymousName() {
        if(null==name)
            return null;
        if(name.length()<=1)
            return "*";
        if(name.length()==2)
            return name.substring(0,1) + "*";

        char[] cs = name.toCharArray();

        // length(), length, size() 注意这三个不同用法
        for (int i = 1; i < cs.length-1; i++) {
            cs[i] = '*';
        }
        return new String(cs);
    }
}
