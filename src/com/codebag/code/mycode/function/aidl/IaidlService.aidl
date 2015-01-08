package com.codebag.code.mycode.function.aidl;
import com.codebag.code.mycode.function.aidl.IaidlClient;

interface IaidlService {
    void doSomethind();
    String getData();
    void regiestCallback(IaidlClient cb);
    void unregiestCallback(IaidlClient cb);
}
