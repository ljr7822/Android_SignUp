package com.example.iwen.factory.data.database.userUpdateDataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.iwen.factory.data.database.userDataBase.UserDao;
import com.example.iwen.factory.data.database.userDataBase.UserDataModel;

/**
 * 数据库类
 *
 * @author iwen大大怪
 * Create to 2021/03/19 0:07
 */
@Database(entities = {UserUpdateDateModel.class}, version = 1, exportSchema = false)
public abstract class UserUpdateDataBase extends RoomDatabase {
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
    public abstract UserUpdateDao getUserUpdateDao();
}
