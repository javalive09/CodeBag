CodeBag
======

A lib for accumulate your codes.


Feature
--------
- support view test unit with just one line code
- verify api or app feature with one line code
- collection your owen code



Import Library
--------

Gradle:
add dependencies as follow
```
compile 'com.javalive09.codebag:codebag:1.4.5'
implementation 'com.javalive09.annotation:annotation:1.0.2'
annotationProcessor 'com.javalive09.processor:processor:1.0'
```

Usage
---------------------
-  Launch
```
CodeBag.Launch(MainActivity);
```
-  Create a file class with annotation of @Test method as the entrance(you can custom class name  @Tester(name = 
"HelloWorld 示例") and custom method name as below)
        
        @Tester
        public class HelloWorld {
        
            @Test
            public void showView() {
                TextView textView = new TextView(PlayerActivity.context());
                textView.setText("hello world!!");
                CodeBag.showView(textView);
            }
        
            @Test
            public void showText() {
                CodeBag.showText("hello world!!");
            }
        
            @Test(name = "显示3.5秒toast")
            public void showToastLong() {
                CodeBag.toastLong("hello world !!");
            }
        
            @Test(name = "显示2秒toast")
            public void showToastShort() {
                CodeBag.toastShort("hello world !!");
            }
        
            @Test(name = "启动其他activity")
            public void startActivity() {
                Intent intent = new Intent(PlayerActivity.context(), PlayerActivity.class);
                CodeBag.startActivity(intent);
            }

![](http://peter-1254131086.file.myqcloud.com/code-helloword-2018-01132130.jpg)

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
