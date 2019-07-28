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
    productionData integer,
    currentData integer

);


  deviceId,waterPumpTypeId,pumpOutput,power,temperature,longitude,latitude,logTime,productionData,currentData

    */



  //定义的输出的字段
  val struct = StructType(
    Array(
      StructField("deviceId",IntegerType),
      StructField("waterPumpTypeId",IntegerType),
      StructField("pumpOutput",IntegerType),
      StructField("power",IntegerType),
      StructField("temperature",IntegerType),
      StructField("longitude",FloatType),
      StructField("latitude",FloatType),
      StructField("logTime",LongType),
      StructField("productionData",IntegerType),
      StructField("currentData",IntegerType)
    )
  )

  /**
   * 根据输入的每一行信息转换成输出的样式
   * @param log  输入的每一行记录信息
   */
  def parseLog(log:String) = {

    try{
      val splits = log.split(",")

      val deviceId = splits(0).toInt
      val waterPumpTypeId = splits(1).toInt
      val pumpOutput = splits(2).toInt
      val power = splits(3).toInt
      val temperature = splits(4).toInt

      val longitude = splits(5).toFloat
      val latitude = splits(6).toFloat
      val logTime = splits(7).toLong
      val productionData = splits(8).toInt
      val currentData = splits(9).toInt

      //这个row里面的字段要和struct中的字段对应上
      Row(deviceId, waterPumpTypeId, pumpOutput, power, temperature,
        longitude, latitude, logTime, productionData, currentData )
    } catch {
      case e:Exception => Row(0)
    }
  }
}
