<?php
/*
 * Created on Jun 24, 2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - PHPeclipse - PHP - Code Templates
 */
	class cosmos_viz_setup{
	
		const SYSTEMS_LIST = 'systems_list';
		private $request;
		private $session;
		private $systems_list;
	

		public function __construct($request){
			$this->request = $request;
			$this->session = $request->getSession(true);
			$this->systems_list = $this->request->getAttribute(self::SYSTEMS_LIST);
		}
	
		public function getSystems(){return $this->systems_list;}
	}
?>
