package tmall.dao;

import java.net.ConnectException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import tmall.bean.Category;
import tmall.bean.Property;
import tmall.bean.Product;
import tmall.util.DBUtil;
import tmall.util.DateUtil;

/**
 * Created by mountain on 2017/9/10.
 */
public class PropertyDAO {

    public int getTotal(int cid) {
        int total = 0;
        String sql = "select count(*) from property where cid = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Property bean) {
        String sql = "insert into property values (null, ?, ?)";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getCategory().getId());
            ps.setString(2, bean.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Property bean) {
        String sql = "update property set cid = ?, name = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, bean.getCategory().getId());
            ps.setString(2, bean.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "delete from property where id = ?";

        try (Connection c =DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    get方法的参数是什么要斟酌，有两种get方式
    public Property get(int id) {
        Property bean = null;
        String sql = "select * from property where id = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bean.setId(id);
                bean.setCategory(bean.getCategory());
                bean.setName(bean.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public Property get(String name, int cid) {
//        这里初始化的bean到底是null还是new
//        Property bean = new Property();
        Property bean = null;
        String sql = "select * from property where name = ? and cid = ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, name);
            ps.setInt(2, cid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bean.setId(bean.getId());
                bean.setCategory(bean.getCategory());
                bean.setName(bean.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public List<Property> list(int cid) {
        return list(cid, 0, Short.MAX_VALUE);
    }

    public List<Property> list(int cid, int start, int count) {
        List<Property> beans = new ArrayList<Property>();
        String sql = "select count(*) from property where cid = ? order by id desc limit ?, ?";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);
            ps.setInt(2, start);
            ps.setInt(3, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Property bean = new Property();
                CategoryDAO category = new CategoryDAO();
                bean.setId(rs.getInt(1));
                bean.setCategory(category.get(rs.getInt(2)));
                bean.setName(rs.getString(3));
                beans.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beans;
    }
}
// 感觉有些import是多余的？后期要用？
