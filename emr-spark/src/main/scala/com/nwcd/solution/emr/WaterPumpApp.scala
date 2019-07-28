package com.nwcd.solution.emr

import java.sql.{Connection, PreparedStatement}

import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.collection.mutable.ListBuffer

/**
 * Schema Infer
 */
object WaterPumpApp {

  def main(args: Array[String]) {



    if(args.length < 2 ){

      println("please input file path :  file:////xxxx_path/water_pump_log.txt" )
      println("please input sql url   :  jdbc:mysql://hostname:3306/table_name?user=xxxx&password=xxxxx  " )
      return ;
    }

    val inputFilePath = args(0)
    val sqlUrl = args(1)
    var accessKey = args(2)
    val secretKey = args(3)

    println("input file path : ", inputFilePath)
    println("input sql url   : ", sqlUrl)


    val spark = SparkSession.builder().appName("SchemaInferApp").master("local[2]").getOrCreate()


//    spark.sparkContext.hadoopConfiguration.set("fs.s3a.access.key", accessKey)
//    spark.sparkContext.hadoopConfiguration.set("fs.s3a.secret.key", secretKey)
//    spark.sparkContext.hadoopConfiguration.set("fs.s3a.endpoint", "s3.us-east-1.amazonaws.com")
//    val rdd = spark.sparkContext.textFile("s3a://dikers.iot/data/water_pump_log.txt")


    val rdd = spark.sparkContext.textFile(inputFilePath)
    print("count:  "+ rdd.count()+"\n")
    //注意：需要导入隐式转换

    val logDF = spark.createDataFrame(rdd.map(x => LogConvertUtil.parseLog(x)),
      LogConvertUtil.struct)

    logDF.printSchema()
    logDF.show(false)



    /**
      * 使用SQL的方式进行统计
      */
    logDF.createOrReplaceTempView("water_pump_log")
    val videoAccessTopNDF = spark.sql("select sum(pumpOutput), sum(power),  count(*), waterPumpTypeId from water_pump_log  GROUP BY waterPumpTypeId")

    videoAccessTopNDF.show(false)




//    initTestData(sqlUrl, logDF)


    spark.stop()
  }


  def initTestData(sqlUrl: String, logDF: DataFrame): Unit ={

    print("initTestData ---------------------------------- \n")
    try {
      logDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[WaterPumpLogStat]

        partitionOfRecords.foreach(info => {
          val deviceId = info.getAs[Int]("deviceId")
          val waterPumpTypeId = info.getAs[Int]("waterPumpTypeId")
          val pumpOutput = info.getAs[Int]("pumpOutput")
          val power = info.getAs[Int]("power")
          val temperature = info.getAs[Int]("temperature")

          val longitude = info.getAs[Float]("longitude")
          val latitude = info.getAs[Float]("latitude")
          val logTime = info.getAs[Long]("logTime")
          val productionData = info.getAs[Int]("productionData")
          val currentData = info.getAs[Int]("currentData")

          /**
            * 不建议大家在此处进行数据库的数据插入
            */

          list.append(WaterPumpLogStat(deviceId,waterPumpTypeId,pumpOutput,power,temperature,
            longitude,latitude,logTime,productionData,currentData))
        })

        insertWaterPumpLogStat(sqlUrl, list)
      })
    } catch {
      case e:Exception => e.printStackTrace()
    }



  }



  def insertWaterPumpLogStat(sqlUrl:String ,  list: ListBuffer[WaterPumpLogStat]): Unit = {

    var connection: Connection = null
    var pstmt: PreparedStatement = null

    try {
      connection = MySQLUtils.getConnection(sqlUrl)

      connection.setAutoCommit(false) //设置手动提交

      val sql = "insert into water_pump_log(deviceId,waterPumpTypeId,pumpOutput,power,temperature,longitude,latitude,logTime,productionData,currentData) values (?,?,?,?,?,?,?,?,?,?) "
      pstmt = connection.prepareStatement(sql)

      for (ele <- list) {
        pstmt.setInt(1, ele.deviceId)
        pstmt.setLong(2, ele.waterPumpTypeId)
        pstmt.setInt(3, ele.pumpOutput)
        pstmt.setInt(4, ele.power)
        pstmt.setInt(5, ele.temperature)

        pstmt.setFloat(6, ele.longitude)
        pstmt.setFloat(7, ele.latitude)
        pstmt.setLong(8, ele.logTime)
        pstmt.setInt(9, ele.productionData)
        pstmt.setInt(10, ele.currentData)
        pstmt.addBatch()
      }

      pstmt.executeBatch() // 执行批量处理
      connection.commit() //手工提交
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(connection, pstmt)
    }
  }

}
