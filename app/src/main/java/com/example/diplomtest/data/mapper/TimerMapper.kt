package com.example.diplomtest.data.mapper

import com.example.diplomtest.data.database.Entity.TimerSessionEntity
import com.example.diplomtest.domain.TimerSessionData

class TimerMapper {

    fun mapModelToEntity(model: TimerSessionData) = TimerSessionEntity(
        id = model.id,
        done = model.done,
        category = model.category,
        durationPlan = model.durationPlan,
        durationFact = model.durationFact
    )
}