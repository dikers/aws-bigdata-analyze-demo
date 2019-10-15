### spark 提交作业

--deploy-mode cluster --name HelloApp --deploy-mode client --class com.nwcd.solution.emr.HelloApp --master yarn s3://dikers.apjc/emr/test-data/spark-1.0.jar file://Users/mac/Desktop/water_pump_log.txt jdbc:mysql://localhost:3306/spark_test?user=root&password=123456 

