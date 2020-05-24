package com.example.streetchampionproject.common.data.databse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetchampionproject.common.data.databse.models.EndedCommandMatchEntity
import io.reactivex.Observable

@Dao
interface EndedCommandMatchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setEndedMatch(endedCommandMatchEntity: List<EndedCommandMatchEntity>)

    @Query("SELECT * FROM ended_command_match WHERE (firstTeamId = :teamId OR secondTeamId = :teamId)")
    fun getEndedMatch(teamId: Int): Observable<List<EndedCommandMatchEntity>>
}
