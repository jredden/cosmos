cosmos
======

collection of software to generate fictitous cosmos entities

To run this software you need MySql version 5.x and resin version 4.x installed.  If you are running apache, you need to turn it off and run resin using port 80.

The software has been developed under Ubuntu Linux.  

The earliest version of the software used swing to generate a range of systems.  The source for that system can still be found in the package com.zenred.cosmos.  com.zenred.cosmos.config and com.zenred.cosmos.bizrule are part of the system.

The original middle tier and backend was generated with middlegen.

com.zenred.cosmos.aop contains aspect driven logging.

com.zenred.cosmos.controller and com.zenred.cosmos.controller.json are controllers in the Spring MVC framework.  New controllers are simple implementations of Controller interface and almost always emit json.

com.zenred.cosmos.dao.base implements the core C.R.U.D. pattern.

com.zenred.cosmos.dao.file contains classes used in the original prototype to write SQL source files to the file system to later be loaded.

com.zenred.cosmos.dao.hibernate is not used.  To Do:  delete.

com.zenred.data_access contains domain specific data access objects.

com.zenred.infra contains deprecated logging.  To Do: replace with slf4j.

com.zenred.service contains service objects that generate, read star clusters and planets.

com.zenred.servlet contains the original non-json Spring controllers.  NillController serves Visualization development and testing.

com.zenred.util contains a no longer used XML interface external to the web site.

com.zenred.visualization contains visualization centric objects.



