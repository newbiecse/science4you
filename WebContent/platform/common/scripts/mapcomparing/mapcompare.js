MapCompare = function () {
	
	var hash = {};
	
	var container, btnCompare, btnNext, btnPrev;
	
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
		
		handleShowContainer();
		
		addNode(specieId);
	}
	
	var onUncheck = function($ele) {
		var specieId = $ele.data('specieid');
		delete hash[specieId];
		
		removeNode(specieId);
		
		handleShowContainer();
	}
	
	var handleShowContainer = function() {
		
		if (Object.keys(hash).length == 0) {
			container.hide();
		} else {
			container.show();
		}
		
		if (Object.keys(hash).length > 1) {
			btnCompare.show();
		} else {
			btnCompare.hide();
		}
	}
	
	var addNode = function(specieId) {
		var eleClone = container.find('.box-clone:first').clone();
		
		eleClone.find('.speciesName').text(hash[specieId].name);
		eleClone.attr('data-specieid', specieId);
		eleClone.removeClass('box-clone');
		container.append(eleClone);
	}
	
	var removeNode = function(specieId) {
		container.find('.specieBox[data-specieid=' + specieId + ']').remove();
	}

	var renderUIPopup = function () {
		
		var keys = Object.keys(hash);
		
		var first = hash[keys[0]];
		var second = hash[keys[1]];
		
	}
	
	var bindEventNextPrev = function () {
		
		btnNext.on('click', function() {
			renderUIPopup();
		});
		
		btnPrev.on('click', function() {
			renderUIPopup();
		});		
	}
	
	var onNext = function () {
		
		renderUIPopup();
		
	}
	
	var buildUICompare = function () {
		
		var $popupContainer = $('#popupCompare');
		var $clone = $popupContainer.find('.popupCompareContent:first').clone();
		
		for(var i = 0; i < 5; i++) {
			
			$popupContainer.append($clone);
		}
		
	    var $list = $popupContainer.find('.popupCompareContent').colorbox({inline:true, rel:'inline', href: function(){
            return $(this).children();
	    }});
	    
	    $list.eq(0).click();
	}
	
	var openPopup = function() {

		buildUICompare();
	}
	
	return {
		init: function() {
			
			$('.plate-map-container').on('change', '.cbx-compare', function(){
				
				var $this = $(this);
				
				if($this.is(':checked')) {
					onCheck($this);
				} else {
					onUncheck($this);
				}
				
			});
			
			container = $('#speciesContainer');
			btnCompare = container.find('#btnCompare');
			btnNext = $('#btn-next');
			btnPrev = $('#btn-prev');
			
			bindEventNextPrev();
			
			container.on('click', '.specieBox', function(){
				
				var $this = $(this);
				
				onUncheck($this);
				
				$('.cbx-compare[data-specieid=' + $this.data('specieid') + ']').prop('checked', false);
			});
			
			btnCompare.on('click', function(){
				
				openPopup();
			});
					
			
		}
	
	}
	
}();