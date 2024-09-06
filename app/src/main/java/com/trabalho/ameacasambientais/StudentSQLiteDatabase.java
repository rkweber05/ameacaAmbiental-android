package com.trabalho.ameacasambientais;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class StudentSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "students.db";
    public static final Integer DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public StudentSQLiteDatabase(Context ctx){
        this.ctx=ctx;
        db = new StudentSQLiteDatabeHelper().getWritableDatabase();
    }

    public static class StudentTable implements BaseColumns{
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_ENDERECO = "endereco";
        public static final String COLUMN_DATA = "data";

        public static String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID                 + " INTEGER PRIMARY KEY, " +
                    COLUMN_DESC         + " TEXT, " +
                    COLUMN_ENDERECO     + " TEXT, " +
                    COLUMN_DATA         + " TEXT)";
            return sql;
        }
    }
    public Long addStudent(Student s){
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_DESC, s.getDesc());
        values.put(StudentTable.COLUMN_ENDERECO, s.getEndereco());
        values.put(StudentTable.COLUMN_DATA, s.getData());

        return db.insert(StudentTable.TABLE_NAME,null,values);
    }

    @SuppressLint("Range")
    public Student getStudent(Long id){
        String cols[] = {StudentTable._ID, StudentTable.COLUMN_DESC,
                StudentTable.COLUMN_ENDERECO, StudentTable.COLUMN_DATA};
        String args[] = {id.toString()};
        Cursor cursor = db.query(StudentTable.TABLE_NAME,cols,
                StudentTable._ID+"=?",args,null,null,StudentTable._ID);

        if(cursor.getCount()!=1){
            return null;
        }

        cursor.moveToNext();
        Student s = new Student();
        s.setId(cursor.getLong(cursor.getColumnIndex(StudentTable._ID)));
        s.setDesc(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_DESC)));
        s.setEndereco(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_ENDERECO)));
        s.setData(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_DATA)));

        return s;

    }

    @SuppressLint("Range")
    public List<Student> getStudents(){
        String cols[] = {StudentTable._ID, StudentTable.COLUMN_DESC,
                StudentTable.COLUMN_ENDERECO, StudentTable.COLUMN_DATA};
        Cursor cursor = db.query(StudentTable.TABLE_NAME,cols,
                null,null,null,null,StudentTable.COLUMN_DESC);
        List<Student> students = new ArrayList<>();
        Student s;

        while (cursor.moveToNext()){
            s = new Student();
            s.setId(cursor.getLong(cursor.getColumnIndex(StudentTable._ID)));
            s.setDesc(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_DESC)));
            s.setEndereco(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_ENDERECO)));
            s.setData(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_DATA)));
            students.add(s);
        }
        return students;
    }

    public Integer removeStudent (Student s){
        String args[] = {s.getId().toString()};
        return db.delete(StudentTable.TABLE_NAME,StudentTable._ID +"=?", args);
    }

    public Integer updateStudent (Student s){
        String args[] = {s.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_DESC, s.getDesc());
        values.put(StudentTable.COLUMN_ENDERECO, s.getEndereco());
        values.put(StudentTable.COLUMN_DATA, s.getData());

        return db.update(StudentTable.TABLE_NAME,values, StudentTable._ID+"=?",args);
    }

    private class StudentSQLiteDatabeHelper extends SQLiteOpenHelper{
        public StudentSQLiteDatabeHelper() { super(ctx, DATABASE_NAME,null,DATABASE_VERSION);}

        @Override
        public void onCreate(SQLiteDatabase db){ db.execSQL(StudentTable.getSQL());}

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME);
            onCreate((db));
        }
    }
}

