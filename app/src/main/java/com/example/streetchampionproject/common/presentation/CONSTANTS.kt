package com.example.streetchampionproject.common.presentation

interface CONSTANTS {

    interface ACTION {
        companion object {
            const val EVENT_GO_BACK = "Go back"
            const val EVENT_GO_MAIN = "Go main"
        }
    }

    interface PROGRESSBAR {
        companion object {
            const val ARG_STATUS_GONE = "Gone"
            const val ARG_STATUS_VISIBLE = "Visible"
        }
    }
}
