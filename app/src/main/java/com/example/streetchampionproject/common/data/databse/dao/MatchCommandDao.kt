package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.MatchCommandEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MatchCommandDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCommandMatchList(commandEntity: List<MatchCommandEntity>)

    @Query("SELECT * FROM match_command WHERE role = :role")
    fun getCommandMatchList(role: String): Single<List<MatchCommandEntity>>

    @Query("DELETE FROM match_command")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCommandMatch(matchCommandEntity: MatchCommandEntity)

    @Query("SELECT * FROM match_command WHERE matchId = :matchId")
    fun getCommandMatch(matchId: Int): Observable<MatchCommandEntity>

    @Query("SELECT * FROM match_command WHERE(firstTeamId =:teamId OR secondTeamId = :teamId)")
    fun getCommandMatches(teamId: Int): Observable<List<MatchCommandEntity>>

}
