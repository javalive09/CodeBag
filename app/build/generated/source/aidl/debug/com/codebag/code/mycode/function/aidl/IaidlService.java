/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/peter/git/CodeBag/app/src/main/aidl/com/codebag/code/mycode/function/aidl/IaidlService.aidl
 */
package com.codebag.code.mycode.function.aidl;
public interface IaidlService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.codebag.code.mycode.function.aidl.IaidlService
{
private static final java.lang.String DESCRIPTOR = "com.codebag.code.mycode.function.aidl.IaidlService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.codebag.code.mycode.function.aidl.IaidlService interface,
 * generating a proxy if needed.
 */
public static com.codebag.code.mycode.function.aidl.IaidlService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.codebag.code.mycode.function.aidl.IaidlService))) {
return ((com.codebag.code.mycode.function.aidl.IaidlService)iin);
}
return new com.codebag.code.mycode.function.aidl.IaidlService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_doSomethind:
{
data.enforceInterface(DESCRIPTOR);
this.doSomethind();
reply.writeNoException();
return true;
}
case TRANSACTION_getData:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getData();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_regiestCallback:
{
data.enforceInterface(DESCRIPTOR);
com.codebag.code.mycode.function.aidl.IaidlClient _arg0;
_arg0 = com.codebag.code.mycode.function.aidl.IaidlClient.Stub.asInterface(data.readStrongBinder());
this.regiestCallback(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unregiestCallback:
{
data.enforceInterface(DESCRIPTOR);
com.codebag.code.mycode.function.aidl.IaidlClient _arg0;
_arg0 = com.codebag.code.mycode.function.aidl.IaidlClient.Stub.asInterface(data.readStrongBinder());
this.unregiestCallback(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.codebag.code.mycode.function.aidl.IaidlService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void doSomethind() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_doSomethind, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getData() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getData, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void regiestCallback(com.codebag.code.mycode.function.aidl.IaidlClient cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_regiestCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unregiestCallback(com.codebag.code.mycode.function.aidl.IaidlClient cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unregiestCallback, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_doSomethind = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_regiestCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unregiestCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void doSomethind() throws android.os.RemoteException;
public java.lang.String getData() throws android.os.RemoteException;
public void regiestCallback(com.codebag.code.mycode.function.aidl.IaidlClient cb) throws android.os.RemoteException;
public void unregiestCallback(com.codebag.code.mycode.function.aidl.IaidlClient cb) throws android.os.RemoteException;
}
