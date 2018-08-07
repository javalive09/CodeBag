CodeBag
======

A lib for accumulate your codes and unit test.


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
implementation 'com.javalive09.codebag:codebag:1.5.8'
implementation 'com.javalive09.annotation:annotation:1.0.5'
annotationProcessor 'com.javalive09.processor:processor:1.6'
```

Usage
---------------------
-  Launch
```
CodeBag.Launch(MainActivity);
```
-  Create a file class with annotation of @Run method(if you want show view the method parameters must be 
CodeActivity) as the entrance and you can 
custom class name  @Code(name = "HelloWorld 示例") and custom method name as below)
        
        
        public class HelloWorld {
        
            @Run
            public void log() {
                Log.e("HelloWorld", "log");
            }
            
            @Run
            public void showView(CodeActivity activity) {
                TextView textView = new TextView(activity);
                textView.setText("hello world!!");
                activity.setContentView(textView);
            }
        
            @Run
            public void showText(CodeActivity activity) {
                activity.showText("hello world!!");
            }
        
            @Run(name = "显示3.5秒toast")
            public void showToastLong(CodeActivity activity) {
                activity.toastLong("hello world !!");
            }
        
            @Run(name = "显示2秒toast")
            public void showToastShort(CodeActivity activity) {
                activity.toastShort("hello world !!");
            }
        
            @Run(name = "启动其他activity")
            public void startActivity(CodeActivity activity) {
                Intent intent = new Intent(activity, CodeActivity.class);
                activity.startActivity(intent);
            }
        
        }
        

![](http://peter-1254131086.costj.myqcloud.com/codebag.png)

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
    
