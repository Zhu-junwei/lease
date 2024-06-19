package com.zjw.lease.web.admin.convert;

import com.zjw.lease.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义转换工厂，用于将字符串转换为基类枚举类型。
 * 该工厂实现了Converter工厂接口，为Spring框架的类型转换系统提供自定义转换器。
 *
 * @author 朱俊伟
 * @since 2024/06/08 01:03
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    /**
     * 为指定的枚举类型生成转换器实例。
     * 转换逻辑基于枚举值的代码与给定字符串进行匹配。
     *
     * @param <T>        目标枚举类型的泛型参数
     * @param targetType 转换目标的枚举类型Class对象
     * @return 字符串到指定枚举类型的转换器实例
     */
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return code -> {
            // 遍历目标枚举类型的所有实例，寻找代码匹配的枚举值
            for (T enumType : targetType.getEnumConstants()) {
                if (enumType.getCode().toString().equals(code)) {
                    return enumType;
                }
            }
            // 若无匹配项，抛出异常指示错误的代码参数
            throw new IllegalArgumentException("未找到匹配的枚举值，code: " + code);
        };
    }
}