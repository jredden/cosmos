
var clusterDrawAPI = (function(){
	
	// private 
	
	var jsGraphic;
	var originX;
	var originY;
	var distance;
	var scale;
	var size;
	var angle;
	
	// public
	
	return {
		setJsGraphic: function(jsgraphic){
			jsGraphic = jsgraphic;
		},
		getJsGraphic: function(){
			return jsGraphic;
		},
		setOriginX: function(originx){
			originX = originx;
		},
		getOriginX: function(){
			return originX;
		},
		setOriginY: function(originy){
			originY = originy;
		},
		getOriginY: function(){
			return originY;
		},
		setDistance: function(dist){
			distance = dist;
		},
		getDistance: function(){
			return distance;
		},
		setScale: function(skale){
			scale = skale;
		},
		getScale: function(){
			return scale;
		},
		setSize: function(syze){
			size = syze;
		},
		getSize: function(){
			return size;
		},
		setAngle: function(angl){
			angle = angl;
		},
		getAngle: function(){
			return angle;
		},
		cons: function(jsGraphic, originX, originY, distance, scale, size, angle){
			this.setJsGraphic(jsGraphic);
			this.setOriginX(originX);
			this.setOriginY(originY);
			this.setDistance(distance);
			this.setScale(scale);
			this.setSize(size);
			this.setAngle(angle);
		}
	}
});


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
		drawOneCluster: function draw(clusterApi, index, maxScale){
			
			var distY = clusterApi.getDistance() * Math.sin(clusterApi.getAngle());
			var distX = clusterApi.getDistance() * Math.cos(clusterApi.getAngle());
			
			var scaledY2;
			scaledY2 = distY == 0 ? 1 : maxScale/distY;
			var scaledX2;
			scaledX2 = distX == 0 ? 1 : maxScale/distX;
			console.log("cluster.distY:" + distY + " cluster.distX:" + distX + " cluster.distance:" + clusterApi.getDistance() + " cluster.scaledX2:" + scaledX2 + " cluster.scaledY2:" + scaledY2);
			scaledY2 = limitY(clusterApi.getScale(), scaledY2);
			scaledX2 = limitX(clusterApi.getScale(), scaledX2);
			
			scaledY2 *= (clusterApi.getScale()/2);
			scaledX2 *= (clusterApi.getScale()/2);
			console.log("scaledX:"+scaledX2+" scaledY:"+scaledY2);
			scaledY2 += clusterApi.getOriginY();
			scaledX2 += clusterApi.getOriginX();
			
			clusterApi.getJsGraphic().setColor(clusterColors[index]);
			clusterApi.getJsGraphic().fillArc(scaledX2, scaledY2, clusterApi.getSize(), clusterApi.getSize(),0,360);
			clusterApi.getJsGraphic().paint();
			console.log("clusterX:"+scaledX2+" clusterY:"+scaledY2);
		},
		largest: function biggestDistance(distanceArray){
			var biggie = 0;
			for(var idex = 0; idex < distanceArray.length; idex++){
				if(Math.abs(distanceArray[idex]) > biggie){
					biggie = Math.abs(distanceArray[idex]);
				}
			}
			return biggie;
		}
	};
})();