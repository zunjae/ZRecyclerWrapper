# ZRecyclerWrapper
Wrapper for RecyclerView

Add it in your root build.gradle at the end of repositories:

```java
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
  
  
Add the dependency:

```java
dependencies {
    compile 'com.github.zunjae:ZRecyclerWrapper:0.11'
}
```

Usage:

```java
new ZRecyclerView(context, recyclerView, adapter)
    .withLayoutManager(LayoutManagerType.GRID)
    .withColumnSizes(3, 5)
    .build();
```

## License

There's no license associated with this project. You may copy the code and modify it to your heart's content. You may not send me questions regarding this project. You're on your own.
