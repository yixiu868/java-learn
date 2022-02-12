### 泛型类
#### 泛型通配符

需要一个在逻辑上可以表示同时是Generic<Integer>和Generic<Number>父类的引用类型。

```java
public void showKeyValue1(Generic<?> obj) {
    System.out.println("泛型 key value is " + obj.getKey());
}
```

类型通配符一般是使用?代替具体的类型实参，注意了，此处?是类型实参，再直白点说，此处的?和Number、String、Integer一样都是一种实际的类型，
可以把?看成所有类型的父类。是一种真实的类型。

当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能，那么可以使用?通配符来标识未知类型。
***

### 泛型接口
***

### 泛型方法

泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型。
