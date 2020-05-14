package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.TeamsEntity
import io.reactivex.Observable

@Dao
interface TeamsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTeam(teamsEntity: TeamsEntity)

    @Query("SELECT * FROM teams WHERE teamId = :id")
    fun getTeam(id: Int): Observable<TeamsEntity>
}
