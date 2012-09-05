#!/bin/sh
cd /home/jredden/workspace/cosmos/target/classes/

export JAVA_HOME=/usr/local/java
export PATH=$JAVA_HOME/bin:$PATH
export PATH=/usr/local/maven/bin:$PATH

(cd ../../src/main/database/schema/
./load_schema.sh
)
# java  -Xms1000m -Xmx2000m com.zenred.cosmos.BuildSystemsBatch 5 5 100003 100003 31 > /home/jredden/tmp/RunTest0_$$.log 2>&1
# java  -Xms1000m -Xmx2000m com.zenred.cosmos.BuildSystemsBatch 5 5 100003 100009 37 > /home/jredden/tmp/RunTest0_$$.log 2>&1
java  -Xms1000m -Xmx2000m com.zenred.cosmos.BuildSystemsBatch 5 1 100003 100008 42 > /home/jredden/tmp/RunTest0_$$.log 2>&1
echo "done RunTest0_$$" >> /home/jredden/tmp/RunTest0_$$.log
# java com.zenred.cosmos.BuildSystemsBatch 20 20 100030 100020 31 > /home/jredden/tmp/RunTest1_$$.log 2>&1
# echo "done RunTest1_$$" >> /home/jredden/tmp/RunTest1_$$.log
# java com.zenred.cosmos.BuildSystemsBatch 20 20 100030 100000 28 > /home/jredden/tmp/RunTest2_$$.log 2>&1
# echo "done RunTest2_$$" >> /home/jredden/tmp/RunTest2_$$.log
# java com.zenred.cosmos.BuildSystemsBatch 20 20 100050 100020 32 > /home/jredden/tmp/RunTest3_$$.log 2>&1
# echo "done RunTest3_$$" >> /home/jredden/tmp/RunTest3_$$.log


for vbl in $(echo *.sql);do echo $vbl;mysql --user=integrat --password=test99a cosmos < $vbl;done  
