package com.daniel.sharding.sphere.shardingdemo03.typehandlers;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MYSQL中多字段和List字段转换器
 *
 * @description 分隔符为英文逗号(,)
 *
 * @author DaiZM
 * @date 2018/04/03
 * @since yswf-2.0
 *
 * @history:
 * <author>          <time>          <version>          <desc>
 * DaiZM             2018/04/03           yswf-2.0           created
 *
 */
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return getStringArray(rs.getString(columnName));
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.getStringArray(rs.getString(columnIndex));
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.getStringArray(cs.getString(columnIndex));
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    String[] parameter, JdbcType jdbcType) throws SQLException {
        //由于BaseTypeHandler中已经把parameter为null的情况做了处理，所以这里我们就不用再判断parameter是否为空了，直接用就可以了
        StringBuilder result = new StringBuilder();
        for (String value : parameter) {
            result.append(value).append(",");
        }
        result.deleteCharAt(result.length() - 1);
        ps.setString(i, result.toString());
    }

    private String[] getStringArray(String columnValue) {

        return columnValue != null ? columnValue.split(",") : null;
    }
}

