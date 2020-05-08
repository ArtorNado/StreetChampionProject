package com.example.streetchampionproject.notification.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.streetchampionproject.R
import com.example.streetchampionproject.api.scs.response.Notification
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.notification_template.view.*

class NotificationListViewHolder(
    override val containerView: View,
    private val clickLambda: (Notification) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(notification: Notification) {
        containerView.tv_sender_name.text = notification.userMainData.userSecondName
        detectNotifText(notification)
        initClickListeners(notification)
    }
    private fun initClickListeners(notification: Notification){
        containerView.btn_arrow.setOnClickListener {
            if (containerView.expandableView.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(containerView.cardView, AutoTransition())
                containerView.expandableView.visibility = View.VISIBLE
                containerView.btn_arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_up)
            } else {
                TransitionManager.beginDelayedTransition(containerView.cardView, AutoTransition())
                containerView.expandableView.visibility = View.GONE
                containerView.btn_arrow.setBackgroundResource(R.drawable.ic_keyboard_arrow_down)
            }
        }
        containerView.btn_positive.setOnClickListener {
            notification.notificationStatus = 1
            clickLambda(notification)
        }
        containerView.btn_negative.setOnClickListener {
            notification.notificationStatus = 2
            clickLambda(notification)
        }
    }

    private fun detectNotifText(notification: Notification){
        when(notification.notificationType){
            1 -> containerView.tv_notification_text.text = notification.userMainData.userFirstName +
                    " " + notification.userMainData.userSecondName + NOTIF_TEXT_TYPE_1
        }
    }

    companion object {

        private const val NOTIF_TEXT_TYPE_1 = " желает присоедениться к вашей команде"

        fun create(parent: ViewGroup, clickLambda: (Notification) -> Unit) =
            NotificationListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.notification_template,
                    parent,
                    false
                ),
                clickLambda
            )
    }
}
