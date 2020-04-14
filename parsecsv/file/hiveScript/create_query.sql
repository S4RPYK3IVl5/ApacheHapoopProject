CREATE EXTERNAL TABLE events
(name string,
price string,
category string,
ip string)
partitioned by (`date` string)

ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
STORED AS TEXTFILE LOCATION '/flume/events';

ALTER TABLE events ADD PARTITION (date='2010-01-01') LOCATION '/flume/events/2010/01/01/';
ALTER TABLE events ADD PARTITION (date='2010-01-02') LOCATION '/flume/events/2010/01/02/';
ALTER TABLE events ADD PARTITION (date='2010-01-03') LOCATION '/flume/events/2010/01/03/';
ALTER TABLE events ADD PARTITION (date='2010-01-04') LOCATION '/flume/events/2010/01/04/';
ALTER TABLE events ADD PARTITION (date='2010-01-05') LOCATION '/flume/events/2010/01/05/';
ALTER TABLE events ADD PARTITION (date='2010-01-06') LOCATION '/flume/events/2010/01/06/';
ALTER TABLE events ADD PARTITION (date='2010-01-07') LOCATION '/flume/events/2010/01/07/';
