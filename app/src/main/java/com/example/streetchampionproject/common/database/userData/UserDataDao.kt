package com.example.streetchampionproject.common.database.userData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface UserDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUserData(userDataEntity: UserDataEntity)

    @Query("SELECT * FROM user_data WHERE userId = :userId")
    fun getUserData(userId: Int): Single<UserDataEntity>
}
