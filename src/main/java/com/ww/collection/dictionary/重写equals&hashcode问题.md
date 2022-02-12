# hashCode、equals不同时重写

[不同时重写equals和hashCode又怎样！](https://mp.weixin.qq.com/s?__biz=MzAwNTM0ODY1Mg==&amp;mid=2457116130&amp;idx=1&amp;sn=8d3b4ac0fdd5dc216d0b1bde95ff3c48&amp;chksm=8c9e31a1bbe9b8b72ec87237cb5bc7e95c84c9b0d65cc603071039e4600fc9d95c57c2f5f658&amp;token=542822643&amp;lang=zh_CN#rd)

代码：com.ww.collection.dictionary.HashCodeAndEqualsQuestion

## hashCode是干嘛的

hashCode方法时根据`对象的地址`生成一个int整数，默认它和地址一一对应的，如果不重写，那么只有对象地址一样的情况下，哈希值才相等。

equals默认用来`比较地址是否相同`

不同时重写hashCode、equals在set、map结构时会遇到问题。

