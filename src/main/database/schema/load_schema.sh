#!/bin/sh
for  vbl in $(echo *.dfn);do echo $vbl;mysql --user=integrat --password=test99a < $vbl;done
