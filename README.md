# osslib-android

Showcase all the open-source libraries that your project uses with a single line of code. **Honestly speaking** I don't like the way the open-source libraries are displayed in projects and I would like to create a better exposure of libraries and more info for the users/developers to act on it.

> I personally use this library to list the projects/libraries that my app use to the users so that they can view and even use these projects in their app.

## How to use this library ?

**This library is works in 2 steps and 1 line of code:**

### 2 steps:

- **Generate** `osslib.json` by using [osslib-web](https://github.com/codekidX/osslib-web) generator. Look at [working](https://github.com/codekidX/osslib-web#working) here.
- **Click** on copy to clipboard option and save it as `osslib.json` into your project assets folder.

### 1 line of code:

#### Kotlin

```kotlin
startActivity(Intent(this@MainActivity, OSSLActivity::class.java))
```

#### Java

```java
startActivity(new Intent(this, OSSLActivity.class))
```

Easy right?!
