# monitor
监控


#### 配置文件
* 语义定义
	* 连接符
		* 或者使用了两种占位符号`|` `,`;并且使用`&`(英文)
	* 匹配方式
		* 目前只支持连接符左右两边正则表达式匹配方式 `com.example.demoes.controller.*Controller|com.example.demoes.*.*Service` 
		* annotation 只会在`method,type`两个配置上生效
		``` 
		示例配置文件:
			{
			  agent: {
				ignore: ".com.typesafe.|com.java.&com.jdk,javax.javafx",
				type: "com.example.demoes.controller.*Controller|com.example.demoes.*.*Service",
				method: "",
				annotation: false
			  }
			}
		```
* 对于外部配置文件
	* `monitor-agent-collect.jar=E:\agent.conf` config指定的路径一定是绝对路径，默认会加载系统agent.conf(会过滤所有文件，也就相当于并未使用该探针)
