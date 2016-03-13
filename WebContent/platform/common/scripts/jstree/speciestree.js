SpeciesTree = function () {
	
	var dom;
	
	var onLoadTree = function () {
		
		dom.jstree({
		    'core' : {
		      'data' : {
		    	  //'url' : 'http://localhost:8080/science4you/region.do?dispatch=searchGroup',
		    	  'url': function(node) {
		    		  
		    		  console.log(node);
		    		  
		    		  if (node.id === '#') {
		    			  return 'http://localhost:8080/science4you/region.do?dispatch=searchGroup';
		    		  } else {
		    			  
		    			  var o1 = dom.jstree(true).get_node(node.id);
		    			  console.log(o1);
		    			  
		    			  var o2 = dom.jstree(true).get_json(node.id);
		    			  console.log(o2);
		    		  }
		    		  
		    		  return 'http://localhost:8080/science4you/region.do?dispatch=searchGroup';
		    	  },
		    	  'dataType': 'json',
		    	  'data': function (node) {
		    		  return { 'id' : node.id };
		    	  },
		    	  'success': function(n) {
		
		    		  for(var i = 0; i < n.length; i++) {

		    			  n[i].li_attr['class'] = 'family-name';
		    			  n[i].a_attr['class'] = 'group';
		    			  n[i].a_attr['type'] = 'group';
		    			  n[i].a_attr['data-type'] = 'group';
		    			  n[i].data['type'] = 'group';
		    			  
		    			  if ($.isArray(n[i].children)) {
		    				  
		    				  n[i].children.forEach(function(child){

		    					  child.li_attr['class'] = 'order-name';
		    					  child.a_attr['class'] = 'order';
		    					  child.a_attr['type'] = 'order';
		    					  child.a_attr['data-type'] = child.type;
		    					  child.data['type'] = child.type;
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