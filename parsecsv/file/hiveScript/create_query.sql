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

CREATE EXTERNAL TABLE countries_blok
(network string,
geoname_id string,
registered_country_geoname_id string,
represented_country_geoname_id string,
is_anonymous_proxy string,
is_satellite_provider string)

ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
STORED AS TEXTFILE LOCATION '/flume/data/block';

CREATE EXTERNAL TABLE countries_location
(geoname_id string,
locale_code string,
continent_code string,
continent_name string,
country_iso_code string,
country_name string,
is_in_european_unionv string)

ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
STORED AS TEXTFILE LOCATION '/flume/data/location';

CREATE TABLE ip_countries as
    SELECT
        cb.network as network,
        cl.country_name as country
    FROM countries_blok as cb JOIN countries_location as cl
    ON (cb.registered_country_geoname_id=cl.geoname_id);
