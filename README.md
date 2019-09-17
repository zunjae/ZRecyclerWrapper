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
    compile 'com.github.zunjae:ZRecyclerWrapper:2.0'
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

This project is available under the MIT license, though there is no need to include a license and copyright notice
