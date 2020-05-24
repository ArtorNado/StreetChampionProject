package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.UserStatusInPlaceEntity
import io.reactivex.Observable

@Dao
interface UserStatusInPlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUserStatus(userStatusInPlaceEntity: UserStatusInPlaceEntity)

    @Query("SELECT * FROM user_status WHERE userId = :userId AND placeId = :placeId")
    fun getUserStatus(userId: Int, placeId: Int): Observable<UserStatusInPlaceEntity>
}
