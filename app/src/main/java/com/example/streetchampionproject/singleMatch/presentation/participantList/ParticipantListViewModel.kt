package com.example.streetchampionproject.singleMatch.presentation.participantList

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
        compositeDisposable.add(
            participantListInteractor.getParticipants(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _participants.value = result
                },
                    { error ->
                        onError(error)
                    })
        )
    }

    fun updateParticipants() {
        compositeDisposable.add(
            participantListInteractor.updateParticipants(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                },
                    { error ->
                        onError(error)
                    })
        )
    }
}
