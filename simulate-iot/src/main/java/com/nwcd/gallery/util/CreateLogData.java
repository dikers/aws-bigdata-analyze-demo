package com.nwcd.gallery.util;

import com.nwcd.gallery.constant.WaterPumpEnum;
import com.nwcd.gallery.domain.WaterPump;
import com.nwcd.gallery.domain.WaterPumpLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateLogData {


    /**
     *  每种型号的水泵有多少台设备
     */
    private static final int WATER_PUMP_COUNT = 10;
    /**
     * 每个设备每天产生多少日志
     */
    private static final int WATER_PUMP_LOG_COUNT = 30;
    /**
     * 产生多少天的日志
     */
    private static final int WATER_PUMP_LOG_DAY = 7 ;

    private static long DEVICE_ID_COUNT = 1000 ;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");




    public static void main(String[] args) throws InterruptedException, IOException {

        CreateLogData createData = new CreateLogData();
        List<WaterPump>  waterPumpList = createData.initWaterPumpFactory();


        for(WaterPump waterPump: waterPumpList){
            System.out.println(waterPump);
        }

        System.out.println();
        List<String> stringList = createData.createLog(waterPumpList);

        createData.writeLogToFile( stringList );
    }


    private List<WaterPump> initWaterPumpFactory(){

        List<WaterPump> waterPumpList = new ArrayList<>( 16 );

        WaterPump waterPump = new WaterPump();
        waterPump.setId( 1 );
        waterPump.setSize( WaterPumpEnum.XXX_SIZE.getSize() );
        waterPump.setLifeCycle(5);

        waterPump.setMaxPower( 1000 );
        waterPump.setMeanPower( 800 );
        waterPump.setPowerErrorRatio( 0.2f );

        waterPump.setMaxPumpOutput( 2000 );
        waterPump.setMeanPumpOutput( 1600 );
        waterPump.setPumpOutputErrorRatio( 0.4f );
        waterPump.setName( WaterPumpEnum.XXX_SIZE.getName() );
        waterPump.setPrice( 3000 );

        waterPumpList.add( waterPump );



        WaterPump waterPump2 = new WaterPump();
        waterPump2.setId( 2 );
        waterPump2.setSize( WaterPumpEnum.XX_SIZE.getSize() );
        waterPump2.setLifeCycle(6);

        waterPump2.setMaxPower( 500 );
        waterPump2.setMeanPower( 300 );
        waterPump2.setPowerErrorRatio( 0.1f );

        waterPump2.setMaxPumpOutput( 1100 );
        waterPump2.setMeanPumpOutput( 700 );
        waterPump2.setPumpOutputErrorRatio( 0.2f );


        waterPump2.setName( WaterPumpEnum.XX_SIZE.getName() );
        waterPump2.setPrice( 1800 );

        waterPumpList.add( waterPump2 );

        return waterPumpList;
    }


    private List<String> createWaterPumpLog(WaterPump waterPump) throws InterruptedException {

        Calendar calendar =  Calendar.getInstance();
        Random random = new Random( System.currentTimeMillis() );
        WaterPumpLog waterPumpLog = new WaterPumpLog();
        List<String> stringList = new ArrayList<>(  );


        waterPumpLog.setDeviceId( DEVICE_ID_COUNT++ );
        waterPumpLog.setProductionData( calendar.get( Calendar.YEAR ) - random.nextInt(7) );
        waterPumpLog.setWaterPumpTypeId( waterPump.getId() );

        int currentDay = calendar.get(Calendar.DATE);

        for(int day = 0; day< WATER_PUMP_LOG_DAY; day ++ ){

            calendar.set( Calendar.DATE, currentDay-day );
            waterPumpLog.setCurrentData( Integer.parseInt(  simpleDateFormat.format(calendar.getTime()) ) );
            for(int i = 0; i< WATER_PUMP_LOG_COUNT + random.nextInt(WATER_PUMP_LOG_COUNT); i++ ){
                waterPumpLog.setPumpOutput( waterPump.getMeanPumpOutput() + random.nextInt((int)(waterPump.getMeanPumpOutput() * waterPump.getPumpOutputErrorRatio())) );
                waterPumpLog.setPower( waterPump.getMeanPower() + random.nextInt((int)(waterPump.getMeanPower() * waterPump.getPowerErrorRatio())) );
                waterPumpLog.setTemperature(  20 + waterPump.getSize() * 5 + random.nextInt(3) );
                waterPumpLog.setLogTime( System.currentTimeMillis() );
                waterPumpLog.setLongitude( 116.40f + random.nextInt(10));
                waterPumpLog.setLatitude( 39.90f + random.nextInt(10) );

                System.out.println(waterPumpLog.toLogString());
                stringList.add( waterPumpLog.toLogString() );



            }
            Thread.sleep( 1 );

        }




        return stringList ;

    }



    public List<String>  createLog(List<WaterPump>  waterPumpList) throws InterruptedException {

        List<String> stringList = new ArrayList<>(  );

        Random random = new Random( System.currentTimeMillis() );
        for(WaterPump waterPump:  waterPumpList){

            for(int i = 0; i< WATER_PUMP_COUNT+ random.nextInt(WATER_PUMP_COUNT) ; i++ ){
                stringList.addAll( createWaterPumpLog(waterPump));
            }
        }
        return stringList;

    }


    public boolean writeLogToFile( List<String> stringList) throws IOException {

        String filePath = "/Users/mac/Desktop/water_pump_log.txt" ;
        File fileName = new File( filePath );

        if(!fileName.exists()){
            fileName.createNewFile();
        }



        RandomAccessFile mm=null;
        boolean flag=false;
        FileOutputStream fileOutputStream=null;
        fileOutputStream = new FileOutputStream(fileName);

        for(String string: stringList){
            fileOutputStream.write( (string+"\r\n").getBytes());
        }
        fileOutputStream.close();
        return true;



    }

}
