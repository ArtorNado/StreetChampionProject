package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.UserDataEntity
import io.reactivex.Observable

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUserData(userDataEntity: UserDataEntity)

    @Query("SELECT * FROM user_data WHERE userId = :userId")
    fun getUserData(userId: Int): Observable<UserDataEntity>
}
