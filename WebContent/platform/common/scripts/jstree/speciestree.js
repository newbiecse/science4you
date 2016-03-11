SpeciesTree = function () {
	
	var dom;
	
	var onLoadTree = function () {
		
		dom.jstree({
		    'core' : {
		      'data' : {
		    	  "url" : "//www.jstree.com/fiddle/?lazy",
		    	  "dataType" : "json",
		    	  "data" : function (node) {
		    		  return { "id" : node.id };
		    	  }		    	  
		      }
		    }
		});		
	}
	
	return {
		init: function () {
			
			dom = $('#speciesTree');
			
			onLoadTree();
		}
	}
}()