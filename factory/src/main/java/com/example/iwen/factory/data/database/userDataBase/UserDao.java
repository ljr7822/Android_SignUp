package com.example.iwen.factory.data.database.userDataBase;

import android.service.autofill.UserData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * 访问数据库操作的接口
 *
 * @author iwen大大怪
 * Create to 2021/03/18 20:21
 */
@Dao
public interface UserDao {
    @Insert
    void insertUser(UserDataModel userDataModel);

    @Update
    void updateUser(UserDataModel userDataModel);

    @Delete
    void deleteUser(UserDataModel userDataModel);

    @Query("DELETE FROM UserDataModel")
    void deleteAllUser();

//    @Query("SELECT * FROM userDataModel ORDER BY workId DESC")
//    void getAllUser();
}
