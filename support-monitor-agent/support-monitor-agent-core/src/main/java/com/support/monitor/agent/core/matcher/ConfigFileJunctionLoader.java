package com.support.monitor.agent.core.matcher;

import com.support.monitor.agent.core.config.ConfigLoader;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

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
    public ElementMatcher.Junction<? super TypeDescription> ignoreJunction() {
        ElementMatcher.Junction<? super TypeDescription> nextJunction =
                Objects.isNull(this.junctionLoader) ? null : this.junctionLoader.ignoreJunction();
        ElementMatcher.Junction<? super TypeDescription> thisJunction = this.interpretOfType(configLoader.ignore());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }
        return thisJunction;
    }

    @Override
    public ElementMatcher.Junction<? super TypeDescription> typeJunction() {
        ElementMatcher.Junction<? super TypeDescription> nextJunction =
                Objects.isNull(this.junctionLoader) ? null : this.junctionLoader.typeJunction();
        ElementMatcher.Junction<? super TypeDescription> thisJunction = this.interpretOfType(configLoader.type());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }
        return thisJunction;
    }

    @Override
    public ElementMatcher.Junction<? super MethodDescription> methodJunction() {
        ElementMatcher.Junction<? super MethodDescription> nextJunction =
                Objects.isNull(this.junctionLoader) ? null : this.junctionLoader.methodJunction();
        ElementMatcher.Junction<? super MethodDescription> thisJunction = this.interpretOfMethod(configLoader.method());
        if (!Objects.isNull(nextJunction)) {
            thisJunction.or(nextJunction);
        }
        return thisJunction;
    }
}
