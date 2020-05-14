package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.SquadEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface SquadDao {

    @Query("SELECT * FROM squad WHERE teamId = :teamId")
    fun getSquad(teamId: Int): Observable<List<SquadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSquad(squadEntity: List<SquadEntity>): Completable
}
