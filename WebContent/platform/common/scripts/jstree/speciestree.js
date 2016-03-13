SpeciesTree = function () {
	
	var dom;
	
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
		    			  
		    			  if ($.isArray(n[i].children)) {
		    				  
		    				  n[i].children.forEach(function(child){

		    					  child.data['type'] = child.type;
		    					  child.li_attr['class'] = 'jstree-node-' + child.type.toLowerCase();
		    					  child.a_attr['class'] = 'jstree-anchor-' + child.type.toLowerCase();		    					  
		    				  })
		    				  
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