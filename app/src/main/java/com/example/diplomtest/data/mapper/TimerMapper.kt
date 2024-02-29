package com.example.diplomtest.data.mapper

import com.example.diplomtest.data.database.TimerSessionEntity
import com.example.diplomtest.domain.TimerSessionData

class TimerMapper {

    fun mapModelToEntity(model: TimerSessionData) = TimerSessionEntity(
        id = model.id,
        done = model.done,
        categoty = model.categoty
    )
}