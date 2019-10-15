package com.nwcd.solution.emr

import org.apache.spark.sql.Row
import org.apache.spark.sql.types._

/**
 * 访问日志转换(输入==>输出)工具类
 */
object LogConvertUtil {

  /**
    *
    * redshift 中建表语句
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


  deviceId,waterPumpTypeId,pumpOutput,power,temperature,longitude,latitude,logTime,productionDate,currentDateRecord

    */



  //定义的输出的字段
  val struct = StructType(
    Array(
      StructField("device_id",IntegerType),
      StructField("water_pump_type_id",IntegerType),
      StructField("pump_output",IntegerType),
      StructField("power",IntegerType),
      StructField("temperature",IntegerType),
      StructField("longitude",FloatType),
      StructField("latitude",FloatType),
      StructField("logTime",LongType),
      StructField("production_date",IntegerType),
      StructField("current_date_record",IntegerType)
    )
  )

  /**
   * 根据输入的每一行信息转换成输出的样式
   * @param log  输入的每一行记录信息
   */
  def parseLog(log:String) = {

    try{
      val splits = log.split(",")

      val device_id = splits(0).toInt
      val water_pump_type_id = splits(1).toInt
      val pump_output = splits(2).toInt
      val power = splits(3).toInt
      val temperature = splits(4).toInt

      val longitude = splits(5).toFloat
      val latitude = splits(6).toFloat
      val logTime = splits(7).toLong
      val production_date = splits(8).toInt
      val current_date_record = splits(9).toInt

      //这个row里面的字段要和struct中的字段对应上
      Row(device_id, water_pump_type_id, pump_output, power, temperature,
        longitude, latitude, logTime, production_date, current_date_record )
    } catch {
      case e:Exception => Row(0)
    }
  }
}
