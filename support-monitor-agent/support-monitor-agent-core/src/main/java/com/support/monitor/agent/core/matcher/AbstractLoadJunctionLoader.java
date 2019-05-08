package com.support.monitor.agent.core.matcher;

import com.support.monitor.agent.core.matcher.expression.IExpression;
import com.support.monitor.agent.core.matcher.expression.NamedFixedExpression;
import com.support.monitor.agent.core.matcher.expression.NoneTerminalExpression;
import lombok.Setter;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
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
     *
     * @param args :
     *             <p>
     *             .cn.|.com&org.,com.example <br>
     *             </p>
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<? super net.bytebuddy.description.type.TypeDescription>
     * @author 江浩
     */
    protected ElementMatcher.Junction<? super TypeDescription> interpretOfType(String args) {
        IExpression expression = new NoneTerminalExpression(args,
                new NamedFixedExpression(),
                new NamedFixedExpression()
        );
        return ElementMatchers.none().and(expression.expression(args));
    }

    protected ElementMatcher.Junction<? super MethodDescription> interpretOfMethod(String args) {
        IExpression expression = new NoneTerminalExpression(args,
                new NamedFixedExpression(),
                new NamedFixedExpression()
        );
        return ElementMatchers.none().and(expression.expression(args));
    }
}
