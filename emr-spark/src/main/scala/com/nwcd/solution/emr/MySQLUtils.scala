package com.nwcd.solution.emr

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
 * MySQL操作工具类
 */
object MySQLUtils {

  /**
   * 获取数据库连接
   */
  def getConnection(url: String) = {
    DriverManager.getConnection(url)
  }

  /**
   * 释放数据库连接等资源
   * @param connection
   * @param pstmt
   */
  def release(connection: Connection, pstmt: PreparedStatement): Unit = {
    try {
      if (pstmt != null) {
        pstmt.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }

}
