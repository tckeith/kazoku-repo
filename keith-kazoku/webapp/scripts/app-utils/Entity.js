define(['raijin', 'backbone'], function(raijin, backbone){
	
	
	var View = Backbone.View.extend({
		initialize: function(args){
			
			raijin.addView(_.extend(this, args));
			
		}
	});
	
	return {
		View : View
	}
	
})
