var clusterAttributes = (function(){
	
	// private
	
	var clusterColors = ["#DAA520","#BA55D3","#FF6347","#7FFF00","#70DBDB"];
	
	// public
	
	return {
		drawOneCluster: function draw(jsGraphic, originX, originY, distance, scale, size, angle, index){
			var distY = distance * Math.sin(angle);
			var distX = distance * Math.cos(angle);
			var scaledY = distY / scale;
			var scaledX = distX / scale;
			
			var scaledY2;
			scaledY2 = scaledY == 0 ? 0 : distY/scaledY;
			var scaledX2;
			scaledX2 = scaledX == 0 ? 0 : distX/scaledX;
			console.log("cluster.distY:" + distY + " cluster.distX:" + distX + " cluster.scaledX:" + scaledX + " cluster.scaledY:" + scaledY + " cluster.scaledX2:" + scaledX2 + " cluster.scaledY2:" + scaledY2);
			
			scaledY2 += originY;
			scaledX2 += originX;
			jsGraphic.setColor(clusterColors[index]);
			jsGraphic.fillArc(scaledX2, scaledY2, size, size,0,360);
			jsGraphic.paint();
		}
	};
})();