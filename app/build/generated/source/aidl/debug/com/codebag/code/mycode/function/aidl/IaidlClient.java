/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/peter/git/CodeBag/app/src/main/aidl/com/codebag/code/mycode/function/aidl/IaidlClient.aidl
 */
package com.codebag.code.mycode.function.aidl;
public interface IaidlClient extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.codebag.code.mycode.function.aidl.IaidlClient
{
private static final java.lang.String DESCRIPTOR = "com.codebag.code.mycode.function.aidl.IaidlClient";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.codebag.code.mycode.function.aidl.IaidlClient interface,
 * generating a proxy if needed.
 */
public static com.codebag.code.mycode.function.aidl.IaidlClient asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.codebag.code.mycode.function.aidl.IaidlClient))) {
return ((com.codebag.code.mycode.function.aidl.IaidlClient)iin);
}
return new com.codebag.code.mycode.function.aidl.IaidlClient.Stub.Proxy(obj);
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
case TRANSACTION_finish:
{
data.enforceInterface(DESCRIPTOR);
this.finish();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.codebag.code.mycode.function.aidl.IaidlClient
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
@Override public void finish() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_finish, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_finish = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void finish() throws android.os.RemoteException;
}