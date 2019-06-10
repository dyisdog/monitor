### APM监控系统
>简介

TODO 需要实现规范
* 参考实现
    * [opentracing-java](https://github.com/opentracing/opentracing-java)
    * [opentracing](https://opentracing.io/specification/)
    * [sofa-tracer](https://github.com/sofastack/sofa-tracer)
```
        [Span A]  ←←←(the root span)
            |
     +------+------+
     |             |
 [Span B]      [Span C] ←←←(Span C is a `ChildOf` Span A)
     |             |
 [Span D]      +---+-------+
               |           |
           [Span E]    [Span F] &gt;&gt;&gt; [Span G] &gt;&gt;&gt; [Span H]
                                       ↑
                                       ↑
                                       ↑
                         (Span G `FollowsFrom` Span F) 
```

```$xslt
––|–––––––|–––––––|–––––––|–––––––|–––––––|–––––––|–––––––|–&gt; time

 [Span A···················································]
   [Span B··············································]
      [Span D··········································]
    [Span C········································]
         [Span E·······]        [Span F··] [Span G··] [Span H··]

```

---
* [设计](https://github.com/dyisdog/files/blob/master/%E8%AE%BE%E8%AE%A1.md)
* [功能规划](https://github.com/dyisdog/files/blob/master/%E5%8A%9F%E8%83%BD%E5%88%97%E8%A1%A8.md)
* [插件列表](https://github.com/dyisdog/files/blob/master/%E6%8F%92%E4%BB%B6%E5%88%97%E8%A1%A8.md)