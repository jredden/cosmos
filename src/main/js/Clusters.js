var clusterAttributes = (function(){
	
	// private
	
	var clusterColors = ["#DAA520","#BA55D3","#FF6347","#7FFF00","#70DBDB"];
	
	// public
	
	return {
		drawOneCluster: function draw(jsGraphic, distance, scale, angle, index){
			var distY = distance * Math.sin(angle);
			var distX = distance * Math.cos(angle);
			var scaledY = distY * scale;
			var scaledX = distX * scale;
			jsGraphic.setColor(clusterColors[idex]);
			jsGraphic.fillArc(scaledX, scaledY, 10,10,0,360);
		}
	};
})();