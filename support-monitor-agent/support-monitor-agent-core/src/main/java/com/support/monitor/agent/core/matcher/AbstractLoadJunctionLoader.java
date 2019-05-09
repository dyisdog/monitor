package com.support.monitor.agent.core.matcher;

import com.support.monitor.agent.core.matcher.expression.NameNoneTerminalExpression;
import com.support.monitor.agent.core.matcher.expression.NameTerminalExpression;
import lombok.Setter;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拦截匹配器加载器
 *
 * @author 江浩
 */
@Setter
public abstract class AbstractLoadJunctionLoader implements IJunctionLoader {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    protected IJunctionLoader junctionLoader;

    public AbstractLoadJunctionLoader(IJunctionLoader junctionLoader) {
        this.junctionLoader = junctionLoader;
    }

    /**
     * 语义解析
     * <p>
     * 1.该方法只支持名字的解析 <br>
     * 2.格式.cn.|.com&org.,com.example <br>
     * </p>
     *
     * @param args : .cn.|.com&org.,com.example
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<net.bytebuddy.description.NamedElement>
     * @author 江浩
     */
    protected ElementMatcher.Junction<NamedElement> interpret(String args) {
        if (StringUtils.isBlank(args)) {
            return ElementMatchers.any();
        }
        return new NameNoneTerminalExpression(new NameTerminalExpression()).expression(args);
    }
}
