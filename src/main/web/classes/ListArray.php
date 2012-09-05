<?php
/*
 * Created on Jan 9, 2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
class ListArray{
	
	private $an_array;
	private $index;
	private $size;
	
	public function __construct() {
	 $this->an_array = array();
	 $this->index = 0;
	}
	
	public function resetIndex(){
	 $this->index = 0;
	}
	
	public function push($thing){
	 array_push($this->an_array, $thing);
	}
	
	public function fetch(){
	 $this->size = count($this->an_array);
	 if($this->size == 0){return false;}
	 $temp_dex = $this->index;
	 if($this->index +1  < $this->size){$this->index += 1;}
	 return $this->an_array[$temp_dex];
	}
	
	public function size(){return count($this->an_array);}
}
?>
