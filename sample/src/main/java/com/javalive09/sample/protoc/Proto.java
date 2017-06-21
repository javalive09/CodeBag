package com.javalive09.sample.protoc;

import com.google.protobuf.ExtensionRegistry;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by peter on 2017/6/21.
 */

public class Proto extends Entry {

    String fileName = "proto.txt";

    public void writeMessage() throws Exception {
        Demo.Person.Builder person = Demo.Person.newBuilder();

        person.setId(1);
        person.setEmail("abcdefg@163.com");
        person.setName("peter");

        //phoneNumber1
        Demo.Person.PhoneNumber.Builder phoneNumber1 = Demo.Person.PhoneNumber.newBuilder();
        phoneNumber1.setNumber("12345678");
        phoneNumber1.setType(Demo.Person.PhoneType.MOBILE);
        //phoneNumber2
        Demo.Person.PhoneNumber.Builder phoneNumber2 = Demo.Person.PhoneNumber.newBuilder();
        phoneNumber2.setNumber("95959595");
        phoneNumber2.setType(Demo.Person.PhoneType.WORK);

        person.addPhone(phoneNumber1);
        person.addPhone(phoneNumber2);

        Demo.AddressBook.Builder addressBook = Demo.AddressBook.newBuilder();
        addressBook.addPerson(person);

        File dir = getActivity().getExternalCacheDir();
        File file = new File(dir, fileName);
        FileOutputStream output = new FileOutputStream(file);
        addressBook.build().writeTo(output);
        output.close();
    }

    public void readMessage() throws Exception {

        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        Demo.registerAllExtensions(registry);

        File dir = getActivity().getExternalCacheDir();
        File file = new File(dir, fileName);
        FileInputStream input = new FileInputStream(file);
        Demo.AddressBook addressBook = Demo.AddressBook.parseFrom(input);
        for (Demo.Person person : addressBook.getPersonList()) {
            Log.i("person id = " + person.getId());
            Log.i("person name = " + person.getName());
            Log.i("person email = " + person.getEmail());

            for (Demo.Person.PhoneNumber phoneNumber : person.getPhoneList()) {
                switch (phoneNumber.getType()) {
                    case MOBILE:
                        Log.i(" Mobile phone #");
                        break;
                    case HOME:
                        Log.i(" Home phone #");
                        break;
                    case WORK:
                        Log.i(" WORK phone #");
                        break;
                }
                Log.i(phoneNumber.getNumber());
            }

//            String info = person.getExtension(Demo.Person.info);
//            Log.i(">> info = " + info);
//            String weight = person.getExtension(Demo.Person.weight);
//            Log.i(">> weight = " + weight);

        }
    }


}
