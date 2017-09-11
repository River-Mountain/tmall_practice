package tmall.dao;

import com.sun.org.apache.xml.internal.security.signature.reference.ReferenceSubTreeData;
import javafx.scene.control.TableView;
import tmall.bean.Category;
import tmall.bean.User;
import tmall.util.DBUtil;

import java.net.ConnectException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by mountain on 2017/9/9.
 */
public class UserDAO {
    public int getTotal() {
        String sql = "select count(*) from user";
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(User bean) {
        String sql = "insert into user values(null, ?, ?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            String name = bean.getName();
            String password = bean.getPassword();
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(User bean) {
        String sql = "update user set name = ?, password = ? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.setString(2, bean.getPassword());
            ps.setInt(3, bean.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User get(int id) {
        User bean = null;
        String sql = "select * from user where id = ?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
//              这里既可以用数字，也可以用名字
                bean.setId(id);
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public User get(String name) {
        User bean = null;
        String sql = "select * from user where name = ?";
        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bean.setName(name);
                bean.setId(rs.getInt(1));
                bean.setPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<User> list(int start, int count) {
        List<User> beans = new ArrayList<User>();

        String sql = "select * from user order by id desc limit ?, ?";

        try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User bean = new User();
                bean.setId(rs.getInt(1));
                bean.setName(rs.getString(2));
                bean.setPassword(rs.getString(3));
                beans.add(bean);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }

    public boolean isExist(String name) {
        User user = get(name);
        return user != null;
    }
}
