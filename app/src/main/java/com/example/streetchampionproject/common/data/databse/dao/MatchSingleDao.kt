package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.MatchSingleEntity
import io.reactivex.Observable

@Dao
interface MatchSingleDao {

    @Query("SELECT * FROM match_single")
    fun getSingleMatch(): Observable<List<MatchSingleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSingleMatchs(matchSingleEntity: List<MatchSingleEntity>)

    @Query("DELETE FROM match_single")
    fun clear()

    @Query("SELECT * FROM match_single WHERE matchId = :matchId")
    fun getSingleMatchById(matchId: Int): Observable<MatchSingleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSingleMatch(matchSingleEntity: MatchSingleEntity)
}
