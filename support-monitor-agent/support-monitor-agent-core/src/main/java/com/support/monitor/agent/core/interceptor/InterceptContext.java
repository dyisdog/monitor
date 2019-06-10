package com.support.monitor.agent.core.interceptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Method;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterceptContext implements Serializable {


    private Object target;

    private Method method;

    @Builder.Default
    private Object[] args = {};

    private boolean ignored;

    private Object result;

    private Throwable throwable;

}
