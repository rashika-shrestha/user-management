package org.example.dao;

import org.example.model.User;
import org.example.util.DbConnectionUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public static final String SAVE_SQL = "insert into user_tbl(user_name, password, salary, mobile_no, dob, is_active)values(?,?,?,?,?,?)";
    public static final String UPDATE_SQL = "update user_tbl set user_name=?, password=?, salary=?, mobile_no=?, dob=?, is_active=? where id=?";
    public static final String DELETE_SQL = "delete from user_tbl where id=?";
    public static final String GET_BY_ID_SQL = "select *  from user_tbl where id=?";
    public static final String LIST_SQL = "select *  from user_tbl";

    @Override
    public int saveUser(User user) {
        int saved = 0;

        try (PreparedStatement ps = DbConnectionUtil.getConnection().prepareStatement(SAVE_SQL)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDouble(3, user.getSalary());
            ps.setLong(4, user.getMobileNo());
            ps.setDate(5, Date.valueOf(user.getDob()));
            ps.setBoolean(6, user.isActive());
            saved = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return saved;
    }

    @Override
    public int updateUser(User user) {
        int updated = 0;

        try (PreparedStatement ps = DbConnectionUtil.getConnection().prepareStatement(UPDATE_SQL)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setDouble(3, user.getSalary());
            ps.setLong(4, user.getMobileNo());
            ps.setDate(5, Date.valueOf(user.getDob()));
            ps.setBoolean(6, user.isActive());
            ps.setInt(7, user.getId());
            updated = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public int deleteUser(int id) {
        int deletd = 0;

        try (PreparedStatement ps = DbConnectionUtil.getConnection().prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            deletd = ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deletd;
    }

    @Override
    public User getUserById(int id) {
        User user = new User();
        try (PreparedStatement ps = DbConnectionUtil.getConnection().prepareStatement(GET_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setSalary(rs.getDouble("salary"));
                user.setMobileNo(rs.getLong("mobile_no"));
                user.setDob(rs.getDate("dob").toLocalDate());
                user.setActive(rs.getBoolean("is_active"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try (PreparedStatement ps = DbConnectionUtil.getConnection().prepareStatement(LIST_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setSalary(rs.getDouble("salary"));
                user.setMobileNo(rs.getLong("mobile_no"));
                user.setDob(rs.getDate("dob").toLocalDate());
                user.setActive(rs.getBoolean("is_active"));
                userList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
