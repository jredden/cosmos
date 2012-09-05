<?php
	require_once('classes/ListArray.php');
/*
 * Created on Jan 9, 2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
	class DataBaseConnection{
		const LOCALHOST = "localhost";
	const USER = 'integrat';
	const PASSWORD = 'test99a';
	const PORT = '3306';
	const SOCKET = '/tmp/mysql.sock';

	private $database;
	private static $self_instance;
	
/* create a connection object which is not connected */
	
	private $mysqli;
	private $result_array;
	
	private function __construct() {	}
	
	public static function instance($database_name){
       	if (!isset(DataBaseConnection::$self_instance)) {
           DataBaseConnection::$self_instance = new DataBaseConnection();
      	}
	DataBaseConnection::$self_instance->database = $database_name;
        return DataBaseConnection::$self_instance;
   	}

	public function query($query){
	
	$this->mysqli = mysqli_init();
	$this->result_array = new ListArray();
	
	/* set connection options */
	
	$this->mysqli->options(MYSQLI_INIT_COMMAND, "SET AUTOCOMMIT=0");
	$this->mysqli->options(MYSQLI_OPT_CONNECT_TIMEOUT, 5);

	/* connect to server */
	printf("database: %s<br/>\n", DataBaseConnection::$self_instance->database);
	$this->mysqli->real_connect(DataBaseConnection::LOCALHOST, DataBaseConnection::USER, DataBaseConnection::PASSWORD, DataBaseConnection::$self_instance->database, DataBaseConnection::PORT, DataBaseConnection::SOCKET);

	/* check connection */
	if (mysqli_connect_errno()) {
	   printf("Connect failed: %sn", mysqli_connect_error());
	   exit();
	}

	$result = $this->mysqli->query($query, MYSQLI_USE_RESULT);
	$ray = new ListArray();
	while ($row = $result->fetch_row()) {
		$ray = new ListArray();
		foreach ($row as $column){
			$ray->push($column);
		}
/*		printf ("size: %s<br/>\n", count($row)); */
		$this->result_array->push($ray);
/*		printf ("%s %s %s %s<br/>%s %s %s %s %s<br/>\n", $row[0], $row[1],$row[2], $row[3],$row[4], $row[5],$row[6], $row[7], $row[8]);*/
	}

	$result->close();
	
	/*printf ("Connection: %s\n.", $mysqli->host_info);*/
	$this->mysqli->close();
	printf ("size: %s<br/>\n", $this->result_array->size());
	return $this->result_array->size();
	}
	
	public function close(){
	 $this->mysqli->close();
	}
	
	public function fetchResult(){return $this->result_array;}
}
?>
