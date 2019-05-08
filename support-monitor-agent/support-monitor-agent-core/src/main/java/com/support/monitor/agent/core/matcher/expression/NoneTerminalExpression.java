package com.support.monitor.agent.core.matcher.expression;

import net.bytebuddy.matcher.ElementMatcher;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;

public class NoneTerminalExpression implements IExpression {

    private static final String OR = "|";

    private static final String OR2 = ",";

    private static final String AND = "&";

    private static final String SPACE = "\\s";

    private IExpression left, right;

    private String content;

    public NoneTerminalExpression(String content, IExpression left, IExpression right) {
        this.content = content;
        this.left = left;
        this.right = right;
    }


    public LinkedList<String> convert(LinkedList<String> nodes, String str) {
        // .cn. | .com & org. | com.example
        int orIndexed = StringUtils.indexOf(str, OR);
        int andIndexed = StringUtils.indexOf(str, AND);
        if (orIndexed < 0 && andIndexed < 0) {
            nodes.add(str);
            return nodes;
        }

        int indexed = (orIndexed < 0 || andIndexed < 0) ?
                Math.max(orIndexed, andIndexed) :
                Math.min(orIndexed, andIndexed);

        String left = StringUtils.substring(str, 0, indexed);
        if (StringUtils.isNotBlank(left)) {
            nodes.add(left);
        }
        return convert(nodes, StringUtils.substring(str, indexed));
    }


    @Override
    public ElementMatcher.Junction expression(String content) {
        //语义解析
        //判定是否是and
        //传递参数 1 2
        //TODO
        return left.expression(content).and(right.expression(content));
    }
}
