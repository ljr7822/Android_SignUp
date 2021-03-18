package com.example.iwen.factory.data.database.userUpdateDataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.iwen.factory.data.database.userDataBase.UserDataModel;

/**
 * 用户更新的数据库操作
 *
 * @author iwen大大怪
 * Create to 2021/03/19 0:05
 */
@Dao
public interface UserUpdateDao {
    @Insert
    void insertUserUpdate(UserUpdateDateModel userUpdateDateModel);

    @Update
    void updateUserUpdate(UserUpdateDateModel userUpdateDateModel);

    @Delete
    void deleteUserUpdate(UserUpdateDateModel userUpdateDateModel);

//    @Query("DELETE FROM UserDataModel")
//    void deleteAllUser();
}
