### 使用规则执行器替换IF条件判断 

有一个小需求需要对之前已有的试用用户申请规则进行拓展。我们的场景大概如下所示：

```java
if (是否海外用户) {
 return false;
}

if (刷单用户) {
  return false;
}

if (未付费用户 && 不再服务时段) {
  return false
}

if (转介绍用户 || 付费用户 || 内推用户) {
  return true;
}
```

按照上述的条件我们可以得出的结论是：

- 咱们的的主要流程主要是基于 and 或者 or 的关系。
- 如果有一个不匹配的话，其实咱们后续的流程是不用执行的，就是需要具备一个短路的功能。
- 对于目前的现状来说，我如果在原有的基础上来改，只要稍微注意一下解决需求不是很大的问题，但是说后面可维护性非常差。

针对这个需求，梳理了一下规则执行器大概的设计。