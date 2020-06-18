package com.yc.dao;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DbHelper {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取数据库的连接
    public Connection getConn() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/res", "root", "13549594523");
        return conn;
    }

    // 关闭资源 （先开启的后关闭，后开启的）
    public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        // 关闭资源
        if (rs != null && !rs.isClosed()) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pstmt != null && !pstmt.isClosed()) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null && !conn.isClosed()) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 更新操作 增删改
     *
     * @param sql
     *            更新语句
     * @param params
     *            传入的参数 不定长的对象数组 传入的参数顺序与？顺序一致
     * @throws SQLException
     *
     */
    public int update(String sql, Object... params) throws SQLException {
        int result = 0;
        try {
            conn = getConn();// 获取连接对象
            pstmt = conn.prepareStatement(sql);
            // 设置参数
            setParamsObject(pstmt, params);
            // 执行
            result = pstmt.executeUpdate();
        } finally {
            closeAll(conn, pstmt, null);
        }
        return result;
    }

    // 不定长参数 设置参数 传入的参数顺序必须和问号的顺序一致
    public void setParamsObject(PreparedStatement pstmt, Object... params) throws SQLException {
        if (params == null || params.length <= 0)
            return;
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]); // 将数组中的第i个元素的值设置为第i + 1 个问号
        }
    }

    public double getCount(String sql, Object... params) throws SQLException {
        double result = 0;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            setParamsObject(pstmt, params);
            rs = pstmt.executeQuery();
            if (rs.next())
                result = rs.getDouble(1);
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return result;
    }

    /**
     * 返回多条记录查询操作 select * from table_name
     *
     *
     * @throws SQLException
     */
    public List<Map<String, Object>> selectMutil(String sql, Object... params) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            // 设置参数
            setParamsObject(pstmt, params);
            // 获取结果集
            rs = pstmt.executeQuery();
            // 根据结果集对象获取到所有结果集中所有列名
            List<String> columnNames = getAllColumnNames(rs);
            while (rs.next()) {
                map = new HashMap<String, Object>();
                Object obj = null; // 获取的值
                // 循环所有的列名
                for (String name : columnNames) {
                    obj = rs.getObject(name);

                    map.put(name, obj);

                    list.add(map);
                }
            }
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * 查询多个记录
     *
     * @param <T>
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> List<T> findMutil(String sql, Class<T> cls ,Object... params) throws Exception {
        List<T> list = new ArrayList<T>();
        T t = null;
        try {
            conn = getConn();
            pstmt = conn.prepareStatement(sql);
            setParamsObject(pstmt, params);
            rs = pstmt.executeQuery();
            Method[] methods = cls.getDeclaredMethods();
            List<String> columnNames = getAllColumnNames(rs);
            while (rs.next()) {
                t = cls.newInstance();
                for (String name : columnNames) {
                    for (Method m : methods) {
                        if (("set" + name).equalsIgnoreCase(m.getName())) {
                            String typeName = m.getParameterTypes()[0].getName();

                            if ("java.lang.Integer".equals(typeName)  ||"int".equals(typeName)) {
                                m.invoke(t, rs.getInt(name));
                            } else if ("java.lang.Double".equals(typeName) ||"double".equals(typeName)) {
                                m.invoke(t, rs.getDouble(name));
                            } else if ("java.lang.Float".equals(typeName)|| "float".equals(typeName)) {
                                m.invoke(t, rs.getFloat(name));
                            } else if ("java.lang.Long".equals(typeName)|| "long".equals(typeName)) {
                                m.invoke(t, rs.getLong(name));
                            }else if ("java.math.BigDecimal".equals(typeName)|| "BigDecimal".equals(typeName)) {
                                m.invoke(t, rs.getBigDecimal(name));
                            } else {
                                m.invoke(t, rs.getString(name));
                            }
                        }
                    }
                }
                list.add(t);
            }
        } finally {
            closeAll(conn, pstmt, rs);
        }
        return list;
    }



    /**
     * 获取查询后的字段名
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public List<String> getAllColumnNames(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<String>();
        ResultSetMetaData data = rs.getMetaData();
        int count = data.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String str = data.getColumnName(i); // 获取指定列的名称
            // 添加列名到 List 集合中
            list.add(str);
        }
        return list;
    }

}
