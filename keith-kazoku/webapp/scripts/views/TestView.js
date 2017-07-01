define(['raijin', 'backbone', 'Entity'], function(raijin, backbone, Entity){
	
	
	function instance(args){
		
		var self,
			View = Entity.View.extend({
				init: function(args){
					self = _.extend(this, args);
					
					debugger;
					
				}
			});
		
		return new View(args)
		
	}
	
	return{
		instance: instance
	}
	
});