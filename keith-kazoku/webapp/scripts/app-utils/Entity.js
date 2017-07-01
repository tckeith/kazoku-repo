/*
 * 
 * Backbone method overrides
 */

define(['raijin', 'backbone'], function(raijin, backbone){
	
	
	var View = Backbone.View.extend({
		initialize: function(args){
			
			raijin.addView(_.extend(this, args));
			
			this.init();
			
		}
	});
	
	return {
		View : View
	}
	
})
