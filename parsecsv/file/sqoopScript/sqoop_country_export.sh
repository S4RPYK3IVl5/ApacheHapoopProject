sqoop export --connect jdbc:mysql://ip-10-0-0-21.us-west-1.compute.internal:3306/asaprykin --driver com.mysql.jdbc.Driver \
--username asaprykin --table top_country --hcatalog-table top_county