MapCompare = function () {
	
	var hash = {};
	
	var onCheck = function ($ele) {
		
		var boxContainer = $ele.closest('.plate-map-container');
		
		var name = boxContainer.find('.plate-map-info-top').text();
		var srcImage = boxContainer.find('.plate-map-innerbox > img:first').attr('src');
		var arrSrcImage = srcImage.split('/');
		var imageName = arrSrcImage[arrSrcImage.length - 1];
		
		var arrImageName = imageName.split('_');
		var specieId = arrImageName[0].substr(2);
		var mapId = arrImageName[1];
		var year = arrImageName[3].split('.')[0];
		
		var o = {
			specieId: specieId,
			mapId: mapId,
			year: year,
			srcImage: srcImage,
			name: name
		}
		
		$ele.attr('data-specieId', specieId);
		
		hash[specieId] = o;
	}
	
	var onUncheck = function($ele) {
		var specieId = $ele.data('specieId');
		delete hash[specieId];
	}
	
	return {
		init: function() {
			
			$('.plate-map-container').on('change', '.cbx-compare', function(){
				
				$this = $(this);
				
				if($this.is(':checked')) {
					onCheck($this);
				} else {
					onUnheck($this);
				}
				
			});
			
		}
	
	}
	
}();