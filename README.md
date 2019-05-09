# monitor
监控


#### 配置文件
* 语义定义
	* 连接符
		* 或者使用了两种占位符号`|` `,`;并且使用`&`(英文)
	* 匹配方式
		* 使用`.`做前后占位操作例如:`.com. | .com|com.`
		* '.'开头结尾，`.com.`加载 `a.com.b,c.com.z` 执行 `nameConstainsWith(com)`
		* '.'只开头,`.com` 加载`a.com,b.com`执行`nameEndWith(com)`
		* '.'只结尾,`com.`加载`com.a,com.b`执行`nameStartWith(com)`
		* "" 前后不含`.`,`com`加载`com`执行`nameEques(com)`
		* annotation 只会在`method,type`两个配置上生效
		* 以`.`开头结尾的将会截取操作如:`.com.cn.=>com.cn`,`.com.cn=>com.cn`,`com.cn.=>com.cn`,如果确实需要有`.`的存在`..com.cn=>.com.cn`
		``` 
		示例配置文件:
			{
			  agent: {
				ignore: ".com.typesafe.|com.java.&com.jdk,javax.javafx",
				type: "com.example.demoes.controller.&.Controller",
				method: "",
				annotation: false
			  }
			}
		```
* 对于外部配置文件
	* `monitor-agent-collect.jar=E:\agent.conf` config指定的路径一定是绝对路径，默认会加载系统agent.conf(会过滤所有文件，也就相当于并未使用该探针)
