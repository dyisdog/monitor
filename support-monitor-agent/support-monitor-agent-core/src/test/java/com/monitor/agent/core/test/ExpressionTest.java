package com.monitor.agent.core.test;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.LinkedList;

public class ExpressionTest {

    @Test
    public void expressionTest() {
        String str = ".cn. | .com&org. , com.example ,com &.cn";
//        System.out.println(StringUtils.substringBefore(str, "|"));
//        System.out.println(StringUtils.substringAfter(str, "|"));
//        System.out.println(StringUtils.replaceChars(str, ",", "|"));
        str = StringUtils.replaceChars(str, ",", "|").replaceAll("\\s", "");

        LinkedList<String> lists = convert(Lists.newLinkedList(), str);
        for (String ls : lists) {
            System.out.println(ls);
        }
    }

    public LinkedList<String> convert(LinkedList<String> nodes, String str) {
        // .cn. | .com & org. | com.example
        int orIndexed = StringUtils.indexOf(str, "|");
        int andIndexed = StringUtils.indexOf(str, "&");
        if (orIndexed < 0 && andIndexed < 0) {
            nodes.add(str);
            return nodes;
        }
        int indexed = (orIndexed < 0 || andIndexed < 0) ?
                Math.max(orIndexed, andIndexed) + 1 :
                Math.min(orIndexed, andIndexed) + 1;

        String left = StringUtils.substring(str, 0, indexed);
        if (StringUtils.isNotBlank(left)) {
            nodes.add(left);
        }
        return convert(nodes, StringUtils.substring(str, indexed));
    }


    @Builder
    @Data
    public static class Node {
        private String left;

        private String right;

        private boolean isAnd;
    }

}
