# Simulator iot



* redshift 建表语句

```


create table water_pump(
    deviceId integer not null,
    waterPumpTypeId smallint,
    pumpOutput integer,
    power integer,
    temperature integer,
    longitude decimal(8,2),
    latitude decimal(8,2),
    logTime bigint,
    productionDateR integer,
    currentDate integer

);


# deviceId,waterPumpTypeId,pumpOutput,power,temperature,longitude,latitude,logTime,productionDate,currentDate


```