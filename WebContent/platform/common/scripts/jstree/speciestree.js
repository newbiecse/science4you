SpeciesTree = function () {
	
	var dom;
	
	var idGroup = null;//2;
	var idOrder = null;//21;
	var idFamily = null;//211;
	var idGenus = null;//2111;
	var idSpecie = null;//21111;
	
	var initValues = function(options) {
				
		if (options !== undefined && options.idSpecie !== undefined) {
			
			idGroup = options.idGroup;
			idOrder = options.idOrder;
			idFamily = options.idFamily;
			idGenus = options.idGenus;
			idSpecie = options.idSpecie;
		}		
	}
	
	var NodeType = {
		ROOT: 'ROOT',
		GROUP: 'GROUP',
		ORDER: 'ORDER',
		FAMILY: 'FAMILY',
		GENUS: 'GENUS',
		SPECIE: 'SPECIE'
	}
	
	var stateLoaded = {
		'loaded': true,
		'opened': true
	}
	
	var onLoadTree = function () {
		
		dom.jstree({
		    'core' : {
		      'data' : {
		    	  'url': function(node) {
		    		  		    		  
		    		  var url = window.location.origin + '/science4you/region.do?dispatch=searchGroup';
		    		  
		    		  if (node.id === '#') {
		    			  url += '&type=ROOT';
		    		  } else {
		    			  
		    			  url += '&type=' + node.data.type;
		    			  url += '&text=' + node.text;		    			  		    			 
		    		  }
		    		  
		    		  return url;
		    	  },
		    	  'dataType': 'json',
		    	  'data': function (node) {
		    		  return { 'id' : node.id };
		    	  },
		    	  'success': function(n) {
		
		    		  for(var i = 0; i < n.length; i++) {

		    			  n[i].data['type'] = n[i].type;
		    			  n[i].li_attr['class'] = 'jstree-node-' + n[i].type.toLowerCase();
		    			  n[i].a_attr['class'] = 'jstree-anchor-' + n[i].type.toLowerCase();		    					  
		    			  
    					  switch(n[i].data['type']) {

    					  case NodeType.ROOT:
							  n[i].state = stateLoaded;
							  
			    			  if ($.isArray(n[i].children)) {
		    				  
			    				  n[i].children.forEach(function(child){
	
			    					  child.data['type'] = child.type;
			    					  child.li_attr['class'] = 'jstree-node-' + child.type.toLowerCase();
			    					  child.a_attr['class'] = 'jstree-anchor-' + child.type.toLowerCase();
			    					  
			    					  if (child.text == idGroup) {
			    						  child.state = stateLoaded;  
			    					  }
			    					  
			    				  })
			    			  }
							  
    						  break;    					  
    					  
    					  case NodeType.GROUP:
    						  if (n[i].text == idGroup) {
    							  n[i].state = stateLoaded;
    						  }
    						  break;
    						  
    					  case NodeType.ORDER:
    						  if (n[i].id == idOrder) {
    							  n[i].state = stateLoaded;
    						  }
    						  break;
    						  
    					  case NodeType.FAMILY:
    						  if (n[i].id == idFamily) {
    							  n[i].state = stateLoaded;
    						  }
    						  break;
    						  
    					  case NodeType.GENUS:
    						  if (n[i].text == idGenus) {
    							  n[i].state = stateLoaded;
    						  }
    						  break;		    						  
    						  
    					  case NodeType.SPECIE:
    						  if (n[i].id == idSpecie) {
    							  n[i].state = {
									  selected: true
    							  };
    						  }
    						  break;    						  
    					  }		    			  
		    			  
		    		  }
		    		  
		    		  return n;                 
		    	  },
		          'themes' : {
		              'icons' : false
		          }
		      }
		    }
		});		
	}
	
	var onSelected = function () {
		
		dom.on('changed.jstree', function (e, data) {
			  var node = data.instance.get_selected(true)[0];
			  
			  if (node.data.type !== 'undefined') {
				  switch(node.data.type) {
					  case NodeType.SPECIE:
						  
						  if (node.id != idSpecie) {
							
								var baseUrl = window.location.origin;
								var navigateUrl = '/science4you/region.do?dispatch=goToSearchPage&specieId=' + node.id;
								
								window.location.href = baseUrl + navigateUrl;
						  }
						  
						  break;
				  }
			  }
		});
	}
	
	return {
		init: function (options) {
			
			dom = $('#speciesTree');
			
			initValues(options);
			
			onLoadTree();
			
			onSelected();
		}
	}
}()