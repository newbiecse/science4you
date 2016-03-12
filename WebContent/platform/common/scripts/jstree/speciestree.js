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
	
	var onSelected = function () {
		
		dom.on("changed.jstree", function (e, data) {
			  console.log(data.instance.get_selected(true)[0].text);
			  console.log(data.instance.get_node(data.selected[0]).text);
		});
	}
	
	return {
		init: function () {
			
			dom = $('#speciesTree');
			
			onLoadTree();
			
			onSelected();
		}
	}
}()