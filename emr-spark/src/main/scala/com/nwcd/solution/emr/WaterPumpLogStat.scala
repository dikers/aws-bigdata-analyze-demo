package com.nwcd.solution.emr

/**
 *  水泵的日志
    create table water_pump(
      deviceId integer not null,
      waterPumpTypeId smallint,
      pumpOutput integer,
      power integer,
      temperature integer,
      longitude decimal(8,2),
      latitude decimal(8,2),
      logTime bigint,
      productionData integer,
      currentData integer

);
 */


case class WaterPumpLogStat(deviceId: Int,
                            waterPumpTypeId: Int,
                            pumpOutput: Int,
                            power: Int,
                            temperature: Int,
                            longitude: Float,
                            latitude: Float,
                            logTime: Long,
                            productionData: Int,
                            currentData: Int
                           )