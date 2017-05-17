CodeBag
======

A simple util for accumulate your codes.

[Demo](https://github.com/javalive09/Sample)

Feature
--------
- verify api or third library feature faster with less code
- collect your own demo code
- show log

Import Library
--------

Gradle:
```
compile 'com.javalive09.codebag:codebag:1.2.8'
```

Usage
---------------------
-  Use com.javalive09.codebag.EntryTreeActivity or its subclass with the theme CodeBagTheme as launcher activity

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.javalive09.sample">
    <application
        android:icon="@drawable/codebag_icon"
        android:label="@string/app_name">
        <activity 
            android:name="com.javalive09.codebag.EntryTreeActivity"
            android:theme="@style/CodeBagTheme">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

-  Create a file extends Entry class with public method as your code entrance (There is no limited for your file and folder’s name and the hierarchy)

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
    
