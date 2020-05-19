package com.example.streetchampionproject.singleMatch.presentation.participantList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetchampionproject.api.scs.models.Participants
import com.example.streetchampionproject.common.presentation.viewModel.BaseViewModel
import com.example.streetchampionproject.singleMatch.domain.interfaces.ParticipantListInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ParticipantListViewModel(
    private val participantListInteractor: ParticipantListInteractor,
    private val matchId: Int
) : BaseViewModel() {


    private val _participants: MutableLiveData<List<Participants>>
            by lazy { MutableLiveData<List<Participants>>() }

    val participants: LiveData<List<Participants>> = _participants

    init {
        getParticipants()
    }

    private fun getParticipants() {
        val result =
            participantListInteractor.getParticipants(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _participants.value = result
                },
                    { error ->
                        Log.e("PART_LIST", "ERROR")
                    })
    }

    fun updateParticipants(){
        val result = participantListInteractor.updateParticipants(matchId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },
                { error ->
                    Log.e("UPDATE_PART_LIST", "ERROR")
                })
    }
}
