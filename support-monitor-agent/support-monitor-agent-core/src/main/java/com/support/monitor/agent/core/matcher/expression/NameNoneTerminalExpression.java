package com.support.monitor.agent.core.matcher.expression;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.Objects;

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

    public LinkedList<String> convert(LinkedList<String> nodes, String content) {
        // .cn. | .com & org. | com.example
        int orIndexed = StringUtils.indexOf(content, OR);
        int andIndexed = StringUtils.indexOf(content, AND);
        if (orIndexed < 0 && andIndexed < 0) {
            nodes.add(content);
            return nodes;
        }
        int indexed = (orIndexed < 0 || andIndexed < 0) ?
                Math.max(orIndexed, andIndexed) + 1 :
                Math.min(orIndexed, andIndexed) + 1;

        String left = StringUtils.substring(content, 0, indexed);
        if (StringUtils.isNotBlank(left)) {
            nodes.add(left);
        }
        return convert(nodes, StringUtils.substring(content, indexed));
    }


    @Override
    public ElementMatcher.Junction<NamedElement> expression(String content) {
        if (Objects.isNull(nameTerminalExpress)) {
            return ElementMatchers.any();
        }
        content = replaceChars(content.replaceAll(SPACE, ""), OR2, OR);
        LinkedList<String> tags = this.convert(Lists.newLinkedList(), content);
        if (CollectionUtils.isEmpty(tags)) {
            return ElementMatchers.any();
        }
        return this.handler(tags, ElementMatchers.any(), 0);
    }

    /**
     * @param tags  :
     * @param index :  当前执行的序号
     * @return : net.bytebuddy.matcher.ElementMatcher.Junction<net.bytebuddy.description.NamedElement>
     * @author 江浩
     */
    private ElementMatcher.Junction<NamedElement> handler(LinkedList<String> tags, ElementMatcher.Junction<NamedElement> junction, int index) {
        if (index >= tags.size()) {
            return junction;
        }
        int tmpIndex = index - 1;
        String preTag = tags.get(tmpIndex <= 0 ? 0 : tmpIndex);
        String thisTag = tags.get(index).replaceAll(TAG_REPLACE, "");

        //当第一个进来的时候 any + 现在的条件 any and
        if (StringUtils.endsWithIgnoreCase(preTag, AND) || index <= 0) {
            junction = junction.and(nameTerminalExpress.expression(thisTag));
        } else {
            junction = junction.or(nameTerminalExpress.expression(thisTag));
        }
        return handler(tags, junction, ++index);
    }

    private String replaceChars(String content, String searchChar, String replaceChar) {
        return StringUtils.replaceChars(content, searchChar, replaceChar).replaceAll(SPACE, "");
    }


}
