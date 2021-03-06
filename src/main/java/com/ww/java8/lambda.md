#### lambda表达式
##### 语法
* 参数、箭头->、以及一个表达式，如果多个表达式式，则使用{}；
* 如果没有参数，使用空的小括号；
    * 例如：() -> { for (int i = 0; i < 1000; i++) doWork(); }
* 如果一个lambda表达式参数类型可以被推导，可以省略参数类型；
    * 例如：(first, second) -> Integer.compare(first.length(), second.length())
* 如果一个方法只有一个参数，并且该参数的类型也可以被推导出来，可以省略小括号；
    * 例如：event -> System.out.println("Thanks for clicking");
* 永远不需要一个lambda表达式执行返回类型，总是可以从上下文中推导出来；

##### 函数式接口
* 对于只包含一个抽象方法的接口，你可以通过lambda表达式来创建该接口的对象，这种接口被称为函数式接口；
* 函数式接口上标注@FunctionalInterface注解，两个好处，首先，编译器会检查标注该注解的实体，检查它是否是只包含一个抽象方法的接口，其次，在javadoc页面也会包含一条声明，说明这个接口是一个函数式接口，该注解并不要求强制使用，使用该注解，会让你的代码看上去更清楚；

##### 方法引用
* ::操作符将方法名和对象或类的名字分隔开来，以下是三种主要的使用情况：
    * 对象::实例方法
    * 类::静态方法
    * 类::实例方法
        * 前提：Lambda 参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数。
* 在前两种情况中，方法引用等同于提供方法的lambda表达式，System.out::println等同于System.out.println(x)
第三种情况中，第一个参数会成为执行方法的对象，例如String::compareToIgnoreCase等同于(x, y) -> x.compareToIgnoreCase(y)
* 还可以捕获方法引用中的this参数，例如，this::equals就等同于x -> this.equal(x)，也可以使用super对象；

##### 构造器引用
* 构造器引用同方法引用类似，不同的是在构造器引用中方法名是new；
    * 前提：构造器的参数列表，需要与函数式接口中参数列表保持一致

##### 变量作用域
* 在lambda表达式中，被引用变量的值不可用被修改；非lambda自由变量，是由外部传入的；

##### 接口静态方法
