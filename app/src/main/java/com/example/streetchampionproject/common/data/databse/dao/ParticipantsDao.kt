package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.ParticipantsEntity
import io.reactivex.Observable

@Dao
interface ParticipantsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setParticipants(participantsEntity: List<ParticipantsEntity>)

    @Query("SELECT * FROM participants WHERE matchId = :matchId")
    fun getParticipants(matchId: Int): Observable<List<ParticipantsEntity>>
}
