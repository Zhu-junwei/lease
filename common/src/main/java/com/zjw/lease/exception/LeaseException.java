package com.zjw.lease.exception;

import com.zjw.lease.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 项目业务异常
 *
 * @author 朱俊伟
 * @since 2024/06/12 08:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeaseException extends RuntimeException{

    private Integer code;

    public LeaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
