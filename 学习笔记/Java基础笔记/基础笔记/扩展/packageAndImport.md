# package
+ 当需要将一个类分类型时使用 package

# import
当需要使用一个外部类时，使用import 导入使用

## 命令行编译执行 

### 基本配置
+ Test1.java 使用`package a;`,`import b.Test2;`
+ Test2.java 使用`package b;`
+ class 位置 `F:\classes`

### 编译运行
+ 如果使用`javac -d . `直接编译，
    - 先编译`Test2.java`
        * 在`Test2.java`位置执行`javac -d . Test2.java`编译,此时会在当前位置生成`b\Test2.class`
    - 编译`Test1.java`
        * 在`Test1.java`位置编译，需将`b`文件夹和`Test2.class`一起拷贝到当前位置，然后编译。
    - 运行`Test1.class`
        * 需将`b`文件夹和`Test2.class`一起拷贝到`a`文件夹并行位置，然后在`a,b`文件夹所在位置执行`java a.Test1`。
    - 原因：编译时如果有`import`是在当前位置开始找引用的Class，运行时是在运行位置查找`运行Class`和`关联引用的Class`。
+ 带编译位置和`class`查找位置。
    - 先编译`Test2.java` 
        * 在`Test2.java`位置执行`javac -d F:\classes -classpath F:\classes Test2.java`编译，此时会在`F:\classes`下产生`b\Test2.class`
    - 编译`Test1.java`
        * 在`Test1.java`位置执行`javac -d F:\classes -classpath F:\classes Test1.java`此时会在`F:\classes`下产生`a\Test1.class`
    - 运行 Test1.class
        * 1、任何位置指定绝对路径运行`java -classpath F:\classes a.Test1`
        * 2、在`a,b`位置执行`java a.Test1`
    - 原因：`-d`指定编译位置，`-classpath`指定了 class 查找位置。