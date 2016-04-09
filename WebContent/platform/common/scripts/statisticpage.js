StatisticPage = function () {

	return {

		init: function() {
			
			var $speciesList = $('.specieslist:first');
			
			$('.sts-group').click(function (){
				
				var $this = $(this);
								
				if (!$this.data('loaded')) {
					
					var $sample = $('#specieSample');
					
					$.ajax({
						url: '/science4you/statistic.do?dispatch=search',
						type: 'POST',
						data: { 
							groupname: $this.data('groupname')
						}					
					}).success(function(data) {
								
						$this.data('loaded', true);
						$this.addClass('expand').removeClass('collapse');
						
						for(var i = data.length - 1; i >= 0; i--) {
							
							var $clone = $sample.clone();
							$clone.removeAttr('id');
							$clone.attr('data-specieid', data[i].id);
							$clone.attr('data-group', $this.data('groupname'));
							
							if (i % 2 == 0) {
								
								$clone.find('td').addClass('TableList');
							}
							
							$clone.find('.wissname').text(data[i].wissname).attr('title', data[i].wissname);
							$clone.find('.trivialname').text(data[i].trivialname);
							$clone.find('.minimumlevel').text(data[i].minimumlevel);
							//$clone.find('.sensitiv').text(data[i].sensitiv);
							$clone.find('.grname').text(data[i].groupname);
						
							$this.after($clone);
							
						};
						
					});
										
				} else {
					
					var group = $this.data('groupname');
					
					if ($this.hasClass('collapse')) {
						
						$this.removeClass('collapse').addClass('expand');
						
						$speciesList.find('.child[data-group="' + group + '"]').show();
						
					} else {
						
						$this.addClass('collapse').removeClass('expand');
						
						$speciesList.find('.child[data-group="' + group + '"]').hide();
					}
					
					
				}
				
				
			});
			
		}
	
	}
	
}();