package com.support.monitor.agent.core.matcher;

import com.support.monitor.agent.core.config.ConfigLoader;
import com.support.monitor.agent.sdk.annotation.AgentCollect;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.Objects;

/**
 * 配置文件加载匹配器
 *
 * @author 江浩
 */
@Slf4j
public class ConfigFileJunctionLoader extends AbstractLoadJunctionLoader {

    private final ConfigLoader configLoader;

    public ConfigFileJunctionLoader(String path, IJunctionLoader junctionLoader) {
        super(junctionLoader);
        LOG.info("config file loader path: {}", path);
        this.configLoader = new ConfigLoader(path);
    }

    public ConfigFileJunctionLoader(IJunctionLoader junctionLoader) {
        super(junctionLoader);
        this.configLoader = new ConfigLoader();
    }

    @Override
    public ElementMatcher.Junction<TypeDescription> ignoreJunction() {
        ElementMatcher.Junction<TypeDescription> nextJunction =
                Objects.isNull(this.junctionLoader) ? ElementMatchers.any() : this.junctionLoader.ignoreJunction();
        ElementMatcher.Junction<NamedElement> thisJunction = this.interpret(configLoader.ignore());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }

        LOG.info("loaded ignore: {}", thisJunction);
        return thisJunction.or(ElementMatchers.isAnnotatedWith(AgentCollect.class));
    }

    @Override
    public ElementMatcher.Junction<TypeDescription> typeJunction() {
        ElementMatcher.Junction<TypeDescription> nextJunction =
                Objects.isNull(this.junctionLoader) ? ElementMatchers.any() : this.junctionLoader.typeJunction();
        ElementMatcher.Junction<NamedElement> thisJunction = this.interpret(configLoader.type());

        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }

        LOG.info("loaded type: {}", thisJunction);
        return thisJunction.or(ElementMatchers.isAnnotatedWith(AgentCollect.class));
    }

    @Override
    public ElementMatcher.Junction<MethodDescription> methodJunction() {
        ElementMatcher.Junction<MethodDescription> nextJunction =
                Objects.isNull(this.junctionLoader) ? ElementMatchers.any() : this.junctionLoader.methodJunction();
        ElementMatcher.Junction<NamedElement> thisJunction = this.interpret(configLoader.method());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }
        LOG.info("loaded method: {}", thisJunction);
        return thisJunction.or(ElementMatchers.isAnnotatedWith(AgentCollect.class));
    }
}
