define(['jquery', 'backbone', 'underscore', 'raijin', 'Entity', 'toastr', 'CryptoJS'],
	function($, Backbone, _, raijin, Entity, toastr, CryptoJS){
	
	
	$(function(){
		
		var Router = Backbone.Router.extend({
			initialize: function(){
				this.routeHits = 0;
				Backbone.history.on('route', function(){this.routeHits++}, this);
			},
			previous: function(){
				if(this.routeHits > 1){
					this.routeHits = this.routeHits - 2;
					window.history.back();
				}
				else {
					if(Backbone.history.getFragment() != '')
						this.routeHits = 0;
					this.navigate('', {trigger:true, replace:true});
				}
			},
			navigateTo: function(route){
				this.navigate('#/'+route);
			},
			routes: {
				'': 'index'
			}
		});
		
		raijin.addRouter(new Router());
		
		raijin.Router().on('route:index', _.debounce(function(){
			
			debugger;
			
		}, 1000, true));
		
		
		$(document).ready(function(){
			
			if (!Backbone.History.started){   Backbone.history.start();	}
			
			$.ajaxSetup({ cache: false });
			
		});
		
	});
	
});