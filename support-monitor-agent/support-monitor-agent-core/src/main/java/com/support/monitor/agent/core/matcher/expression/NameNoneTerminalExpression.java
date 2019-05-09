package com.support.monitor.agent.core.matcher.expression;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;

/**
 * 参数解析，入口解析位置
 *
 * @author 江浩
 */
@Slf4j
public class NameNoneTerminalExpression implements IExpression {

    private static final String OR = "|";

    private static final String OR2 = ",";

    private static final String AND = "&";

    private static final String SPACE = "\\s*|\\t|\\r|\\n";

    private static final String TAG_REPLACE = "\\" + OR + "|" + AND + "";

    private IExpression nameTerminalExpress;

    public NameNoneTerminalExpression(IExpression nameTerminalExpress) {
        this.nameTerminalExpress = nameTerminalExpress;
    }

    @Override
    public ElementMatcher.Junction<NamedElement> expression(String content) {
        content = StringUtils.replaceChars(content.replaceAll(SPACE, ""), OR2, OR);
        LinkedList<String> regex = matchRegex(Lists.newLinkedList(), content);
        return this.handler(regex, ElementMatchers.any(), 0);
    }

    private ElementMatcher.Junction<NamedElement> handler(LinkedList<String> regexs, ElementMatcher.Junction<NamedElement> junction, int index) {
        if (index >= regexs.size()) {
            return junction;
        }
        int tmpIndex = index - 1;
        String preTag = regexs.get(tmpIndex <= 0 ? 0 : tmpIndex);
        String thisTag = regexs.get(index).replaceAll(TAG_REPLACE, "");
        //当第一个进来的时候 any + 现在的条件 any and
        if (StringUtils.isBlank(thisTag)) {
            return junction;
        }
        if (StringUtils.endsWithIgnoreCase(preTag, AND) || index <= 0) {
            junction = junction.and(nameTerminalExpress.expression(thisTag));
        } else {
            junction = junction.or(nameTerminalExpress.expression(thisTag));
        }
        return handler(regexs, junction, ++index);
    }

    private LinkedList<String> matchRegex(LinkedList<String> regex, String content) {
        int orIndexed = StringUtils.indexOf(content, OR);
        int andIndexed = StringUtils.indexOf(content, AND);
        if (orIndexed < 0 && andIndexed < 0) {
            regex.add(content);
            return regex;
        }
        int indexed = (orIndexed < 0 || andIndexed < 0) ?
                Math.max(orIndexed, andIndexed) + 1 :
                Math.min(orIndexed, andIndexed) + 1;

        String left = StringUtils.substring(content, 0, indexed);
        if (StringUtils.isNotBlank(left)) {
            regex.add(left);
        }
        return matchRegex(regex, StringUtils.substring(content, indexed));
    }

}
