package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.UserStatusInTeamEntity
import io.reactivex.Observable

@Dao
interface UserStatusInTeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setUserStatus(userStatusInTeamEntity: UserStatusInTeamEntity)

    @Query("SELECT * FROM user_status WHERE userId = :userId AND teamId = :teamId")
    fun getUserStatus(userId: Int, teamId: Int): Observable<UserStatusInTeamEntity>
}
