package com.support.monitor.agent.core.interceptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterceptContext {

    private Object target;

    private Method method;

    private Object[] args;
}
