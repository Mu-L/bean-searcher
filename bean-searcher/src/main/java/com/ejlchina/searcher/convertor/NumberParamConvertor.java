package com.ejlchina.searcher.convertor;

import com.ejlchina.searcher.ParamResolver;
import com.ejlchina.searcher.bean.DbType;
import com.ejlchina.searcher.util.StringUtils;

import java.math.BigDecimal;

/**
 * [String | Number to Number] 参数值转换器
 *
 * @author Troy.Zhou @ 2022-06-14
 * @since v3.8.0
 */
public class NumberParamConvertor implements ParamResolver.Convertor {

    @Override
    public boolean supports(DbType dbType, Class<?> valueType) {
        return (
            dbType == DbType.BYTE || dbType == DbType.SHORT || dbType == DbType.INT || dbType == DbType.LONG ||
            dbType == DbType.FLOAT || dbType == DbType.DOUBLE || dbType == DbType.DECIMAL
        ) && (
            String.class == valueType || Byte.class == valueType || Short.class == valueType ||
                    Integer.class == valueType || Long.class == valueType || Float.class == valueType ||
                    Double.class == valueType || BigDecimal.class == valueType
        );
    }

    @Override
    public Object convert(DbType dbType, Object value) {
        if (value instanceof String) {
            String s = (String) value;
            if (StringUtils.isBlank(s)) {
                return null;
            }
            switch (dbType) {
                case BYTE:
                    return Byte.parseByte(s);
                case SHORT:
                    return Short.parseShort(s);
                case INT:
                    return Integer.parseInt(s);
                case LONG:
                    return Long.parseLong(s);
                case FLOAT:
                    return Float.parseFloat(s);
                case DOUBLE:
                    return Double.parseDouble(s);
                case DECIMAL:
                    return new BigDecimal(s);
            }
        }
        if (value instanceof Number) {
            Number num = (Number) value;
            switch (dbType) {
                case BYTE:
                    return num.byteValue();
                case SHORT:
                    return num.shortValue();
                case INT:
                    return num.intValue();
                case LONG:
                    return num.longValue();
                case FLOAT:
                    return num.floatValue();
                case DOUBLE:
                    return num.doubleValue();
            }
            // 转为 BigDecimal
            if (num instanceof BigDecimal) {
                return num;
            }
            if (num instanceof Byte || num instanceof Short || num instanceof Integer || num instanceof Long) {
                return new BigDecimal(num.longValue());
            }
            if (num instanceof Float || num instanceof Double) {
                return BigDecimal.valueOf(num.doubleValue());
            }
        }
        return null;
    }
}
