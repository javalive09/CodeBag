CodeBag
======

A simple util for programmer collect demos.
[sample](https://github.com/javalive09/Sample)

Download
--------

Gradle:
```
compile 'com.javalive09.codebag:codebag:1.1.7'
```

How Collect your code
---------------------

-  Keep Your Project AndroidManifest.xml application Tag with no Attribute
-  Keep Your Project AndroidManifest.xml have no launcher activity
-  Create a file extends Entry class with public method as your code entrance (There is no limited for your file and folder’s name and the hierarchy)

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.javalive09.sample">
    <application>
        <activity android:name="com.javalive09.sample.helloworld.HelloWorldActivity"/>
        ... your others components
    </application>
</manifest>
```

        public class HelloWorld extends Entry {
            
            //show text
            public void showText() {
                showTxt("hello world !");
            }
            
            //show view
            public void showView() {
                Button button = new Button(getActivity());
                button.setText("hello world !");
                button.setTextColor(Color.BLUE);
                showView(button);
            }
            
            //show view by id
            public void showViewById() {
                showView(R.layout.helloworld_method_layout);
            }
            
            //start activity
            public void startActivity() {
                Intent intent = new Intent(getActivity(), HelloWorldActivity.class);
                startActivity(intent);
            }
            
            //show log
            public void printLog() {
                LogUtil.i("hello world !");
                LogUtil.w("hello world !");
                LogUtil.e("hello world !");
            }
        }

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
    
