var clusterAttributes = (function(){
	
	// private
	
	var clusterColors = ["#DAA520","#BA55D3","#FF6347","#7FFF00","#70DBDB"];
	
	function limitX(scale, scaledX){
		var answer = scaledX < -scale/2 ? -scale/2 : scaledX;
		return answer > scale/2 ? scale/2 : answer;
	}
	function limitY(scale, scaledY){
		var answer = scaledY < -scale/2 ? -scale/2 : scaledY;
		return answer > scale/2 ? scale/2 : answer;
	}
	
	// public
	
	return {
		drawOneCluster: function draw(jsGraphic, originX, originY, distance, scale, size, angle, index, maxScale){
			var distY = distance * Math.sin(angle);
			var distX = distance * Math.cos(angle);
			
			var scaledY2;
			scaledY2 = distY == 0 ? 0 : maxScale/distY;
			var scaledX2;
			scaledX2 = distX == 0 ? 0 : maxScale/distX;
			console.log("cluster.distY:" + distY + " cluster.distX:" + distX + " cluster.distance:" + distance + " cluster.scaledX2:" + scaledX2 + " cluster.scaledY2:" + scaledY2);
			scaledY2 = limitY(scale, scaledY2);
			scaledX2 = limitX(scale, scaledX2);
			
			scaledY2 *= (scale/2);
			scaledX2 *= (scale/2);
			scaledY2 += originY;
			scaledX2 += originX;
			
			jsGraphic.setColor(clusterColors[index]);
			jsGraphic.fillArc(scaledX2, scaledY2, size, size,0,360);
			jsGraphic.paint();
			console.log("clusterX:"+scaledX2+" clusterY:"+scaledY2);
		}
	};
})();