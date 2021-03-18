package com.example.iwen.factory.data.database.userDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 数据库类
 *
 * @author iwen大大怪
 * Create to 2021/03/18 20:28
 */
@Database(entities = {UserDataModel.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {
//    // 单例模式
//    private static UserDataBase INSTANCE;
//    static synchronized UserDataBase getInstance(Context context){
//        if (INSTANCE == null){
//            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserDataBase.class,"user_database")
//                    .build();
//        }
//        return INSTANCE;
//    }
    // 获取UserDao
    public abstract UserDao getUserDao();
}
