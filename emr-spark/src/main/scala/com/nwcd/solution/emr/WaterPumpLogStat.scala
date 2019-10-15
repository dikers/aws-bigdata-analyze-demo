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
      productionDate integer,
      currentDateRecord integer

);
 */


case class WaterPumpLogStat(device_id: Int,
                            water_pump_type_id: Int,
                            pump_output: Int,
                            power: Int,
                            temperature: Int,
                            longitude: Float,
                            latitude: Float,
                            logTime: Long,
                            production_date: Int,
                            current_date_record: Int
                           )