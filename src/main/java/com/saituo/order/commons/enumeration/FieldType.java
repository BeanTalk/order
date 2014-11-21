package com.saituo.order.commons.enumeration;

import java.util.Date;

/**
 * 字段类型枚举
 */
public enum FieldType implements ValueEnum<Class<?>> { 

    /**
     * String 类型
     */
    STRING("string",String.class),
    /**
     * Integer 类型
     */
    INTEGER("integer",Integer.class),
    /**
     * Long 类型
     */
    LONG("long",Long.class),
    /**
     * Double 类型
     */
    DOUBLE("double",Double.class),
    /**
     * Date 类型
     */
    DATE("date", Date.class),
    /**
     * Boolean 类型
     */
    BOOLEAN("boolean", Boolean.class);

    // 名称
    private String name;

    // 类型
    private Class<?> value;

    /**
     * 字段类型枚举
     *
     * @param name 名称
     * @param value 类型
     */
    private FieldType(String name, Class<?> value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Class<?> getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * 获取 Class 对象
     *
     * @param name 类名称,参考{@link com.saituo.order.commons.enumeration.FieldType}
     *
     * @return 对应的 Clsas 对象
     */
    public static Class<?> getClass(String name) {

        for (FieldType fieldType : FieldType.values()) {
            if (fieldType.getName().equals(name)) {
                return fieldType.getValue();
            }
        }

        return null;
    }
}
