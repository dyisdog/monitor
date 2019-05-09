package com.support.monitor.agent.core.matcher;

import com.support.monitor.agent.core.config.ConfigLoader;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.NamedElement;
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
    public ElementMatcher.Junction<NamedElement> ignoreJunction() {
        ElementMatcher.Junction<NamedElement> nextJunction =
                Objects.isNull(this.junctionLoader) ? ElementMatchers.any() : this.junctionLoader.ignoreJunction();
        ElementMatcher.Junction<NamedElement> thisJunction = this.interpret(configLoader.ignore());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }

        LOG.info("loaded ignore: {}", thisJunction);
        return thisJunction;
    }

    @Override
    public ElementMatcher.Junction<NamedElement> typeJunction() {
        ElementMatcher.Junction<NamedElement> nextJunction =
                Objects.isNull(this.junctionLoader) ? ElementMatchers.any() : this.junctionLoader.typeJunction();
        ElementMatcher.Junction<NamedElement> thisJunction = this.interpret(configLoader.type());

        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }

        LOG.info("loaded type: {}", thisJunction);
        return thisJunction;
    }

    @Override
    public ElementMatcher.Junction<NamedElement> methodJunction() {
        ElementMatcher.Junction<NamedElement> nextJunction =
                Objects.isNull(this.junctionLoader) ? ElementMatchers.any() : this.junctionLoader.methodJunction();
        ElementMatcher.Junction<NamedElement> thisJunction = this.interpret(configLoader.method());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }
        LOG.info("loaded method: {}", thisJunction);
        return thisJunction;
    }
}
