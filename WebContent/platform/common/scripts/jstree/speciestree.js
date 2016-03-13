SpeciesTree = function () {
	
	var dom;
	
	var idGroup = 2;
	var idOrder = 21;
	var idFamily = 211;
	var idGenus = 2111;
	var idSpecie = 21111;
	
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
		    		  
		    		  if (node.id === '#') {
		    			  return 'http://localhost:8080/science4you/region.do?dispatch=searchGroup';
		    		  } else {
		    			  
		    			  var url = 'http://localhost:8080/science4you/region.do?dispatch=searchGroup';
		    			  url += '&type=' + node.data.type;
		    			  
		    			  return url;
		    		  }
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
			    					  
			    					  child.state = stateLoaded;
			    				  })
			    			  }
							  
    						  break;    					  
    					  
    					  case NodeType.GROUP:
    						  if (n[i].id == idGroup) {
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
    						  if (n[i].id == idGenus) {
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
						  
						  alert('navigation to url')
						  break;
				  }
			  }
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