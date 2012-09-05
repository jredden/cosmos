package main;
use Carp;
use strict;

my($rundown) = (defined $ARGV[0])? $ARGV[0] : 100;
my($idex);

for($idex = 0; $idex < $rundown; $idex++){
	carp("java command failed") unless print`java com.zenred.cosmos.StarAtributes`, "\n" ;
}