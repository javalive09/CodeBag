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
```
compile 'com.javalive09.codebag:codebag:1.4.1'
```

Usage
---------------------
-  Launch
```
CaseActivity.Launch(this);
```
-  Create a file class with annotation of @play method as the entrance(you can custom class name  @Player(name = "HelloWorld 示例") and custom method name as below)

        public class HelloWorld {
        
            @Play
            public void showView() {
                TextView textView = new TextView(PlayerActivity.context());
                textView.setText("hello world!!");
                PlayerActivity.context().showView(textView);
            }
        
            @Play
            public void showText() {
                PlayerActivity.context().showText("hello world!!");
            }
        
            @Play(name = "显示3.5秒toast")
            public void showToastLong() {
                PlayerActivity.context().toastLong("hello world !!");
            }
        
            @Play(name = "显示2秒toast")
            public void showToastShort() {
                PlayerActivity.context().toastShort("hello world !!");
            }
        
            @Play(name = "启动其他activity")
            public void startActivity() {
                Intent intent = new Intent(PlayerActivity.context(), PlayerActivity.class);
                PlayerActivity.context().startActivity(intent);
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
    
