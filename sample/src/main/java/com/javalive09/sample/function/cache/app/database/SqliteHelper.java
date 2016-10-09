package com.javalive09.sample.function.cache.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "test.db";
	private static final int version = 0;
	
	public SqliteHelper(Context context) {
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE person (personid integer primary key autoincrement, name varchar(20)), age integer");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//增加新表 或 重命名表名 表结果变化 增加列（数据库结构变化）
		if(oldVersion < 2) {//version 2 有变化节点()
			db.execSQL("ALTER TABLE person ADD COLUMN hight integer");
		}
		
		if(oldVersion < 3) {//version 3 有变化节点
			db.execSQL("ALTER TABLE person ADD COLUMN age integer");
		}
		
		if(oldVersion < 4) {
			db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL ");
		}
	}

	public void insert(Person person) {
//		db.execSQL("insert into person (name,phone) values (?,?)", new Object[] { "peter", "123"});
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("insert into person (name, age) values (?,?)", new Object[] { person.name, person.age});
		
	}
	
	public void insert2(Person person) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.name);
		values.put("age", person.age);
		// 如果 content values为空
		db.insert("person", null, values); // 组拼sql语句完成的添加的操作
	}
	
	public void delete(Person person) {
		SQLiteDatabase db = getReadableDatabase();
		db.delete("person", "name=?", new String[] { person.name });
		db.close();
	}
	
	public void update(Person person) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("update person set name=? , age=? where name=?", new Object[] { person.name, person.age });
		db.close();
	}
	
	public void update2(Person person) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", person.name);
		values.put("age", person.age);
		db.update("person", values, "name=?", new String[] { person.name });
		db.close();
	}
	
	public Cursor query(Person person) {
		SQLiteDatabase db = getReadableDatabase();
		return db.rawQuery("select * from person where name=?", new String[] { person.name });
	}
	
	public Cursor query2(Person person) {
		SQLiteDatabase db = getReadableDatabase();
		return db.query("person", null, "name=?", new String[] { person.name }, null, null, null);
	}
	
	public Cursor queryAll(Person person) {
		SQLiteDatabase db = getReadableDatabase();
		return db.rawQuery("select * from person", null);
	}
	
	public Cursor queryAll2(Person person) {
		SQLiteDatabase db = getReadableDatabase();
		return db.query("person", null, null, null, null, null, null);
	}
	
	public int queryCount() {
		int count = 0;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		if(cursor.moveToNext()) {
		    int index = cursor.getColumnIndex("columnName");
			count = cursor.getInt(index);
		}
		cursor.close();
		db.close();
		return count;
	}
}
