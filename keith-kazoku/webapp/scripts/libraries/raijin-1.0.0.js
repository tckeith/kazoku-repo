/**
 * Raijin Framework.
 *
 * @project Raijin
 *
 * @namespace raijin
 *
 * @version 1.0.0
 */
define('raijin',['require','underscore','jquery','blockui'], function( require ) {
	 var _ = require('underscore'),
     $ = require('jquery');
	 
	 var Raijin, eventHub;
	 
	 	/*Base Model*/
	    Raijin = function(config) {
	    	
	    	var views = [], viewmodels = [], router = null;
	    	var exportName, previousObject;
	    	eventHub = new EventHub();
	    	
	    	this.id = (Math.random());
	    	
			this.addViewModel = function( viewmodel ){
				var idx, maxIdx, vm,
	    		results = [];
				
				if(viewmodels[viewmodel['name']])
					return;
				
				viewmodel['publish'] = eventHub.postEvent;

		    	if( arguments.length === 1 && _.isArray( viewmodel ) ) {
		    		return this.addViewModel.apply( this, viewmodel );
		    	}

		    	for( idx = 0, maxIdx = arguments.length; idx < maxIdx; idx++ ) {
		    		vm = arguments[idx];
		    		if( vm.name) {
		    			viewmodels[vm.name] = vm;
		    		}

		    		results.push(vm);
		    	};
		    	
		    	if(viewmodel['subscriptions'])
		    		eventHub.subscribeEvents.apply(eventHub, arguments);
		    	
		    	vm['status'] = 'initialized';

		    	return arguments.length === 1 ? results[0] : results;
			};
			
			this.addView =function( view ){
				var idx, maxIdx, vw,
	    		results = [];
				
				if(views[view['name']])
					return;
				
		    	if( arguments.length === 1 && _.isArray( view ) ) {
		    		return this.addView.apply( this, view );
		    	}

		    	for( idx = 0, maxIdx = arguments.length; idx < maxIdx; idx++ ) {
		    		vw = arguments[idx];
		    		if( vw.name) {
		    			views[vw.name] = vw;
		    		}

		    		results.push(vw);
		    	};
		    	
		    	vw['status'] = 'initialized';

		    	if(view['subscriptions'])
				eventHub.subscribeEvents.apply(eventHub, arguments);
				
		    	return arguments.length === 1 ? results[0] : results;
			};
			
			this.getView = function( name ){
				return views[name];
			};
			
			this.getViewModel = function ( name ){
				return viewmodels[name];
			};
			
			this.addRouter = function(router){
				this.router = router;
			}
			
			this.getRouter = function(){
				return this.router;
			}
			
			/*Functional*/
			this.startBusyOn = function (element){
				// Start Blocking Component
				$('#' + element).block({
					message: "&nbsp;",
					css: {
						padding:0, 	margin:0,			width:'40%',	top:'30%',
						left:'35%',	cursor:'wait',		color:'#000',	border:'0px',
						backgroundColor:'none',			textAlign:'center'
					},
					overlayCSS: { backgroundColor: '#ffffff', opacity:0, cursor:'wait' },
					iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false':'about:blank',
					cursorReset:'default', forceIframe:false, baseZ:2000, centerX:true, centerY:true, allowBodyStretch:true,
					bindEvents:true, constrainTabKey:true, fadeIn:200, fadeOut:200, timeout:0, showOverlay:true, focusInput:true,
					onBlock:null, onUnblock:null, quirksmodeOffsetHack:4, blockMsgClass:'busyComponent', ignoreIfBlocked:false
				});
			};
			
			this.stopBusyOn = function (element){
				$('#' + element).unblock();
			};
			
			this.onError = function (error){
				
				console.log(error.message);
				
			};
			
			window.addEventListener('error', this.onError);
			
			if( config ) {
	            for( key in config ) {
	                value = config[key];
	                switch( key ) {
	                case 'exportName':
	                	exportName = value;
	                	previousObject = window[exportName];
	                	window[exportName] = this;
	                	break;

	                default:
	                    this[key] = value;
	                	break;
	                };
	            };
	        }
	    
	    };
	    
	    /*
	     * Handle events
	     */
	    EventHub  = function()
	    {
	    	/*
	    	 * Listener based on instance name
	    	 */
	    	this.subscribeEvents = function()
	    	{
	    		var instance = arguments[0];
	    		_.each(instance.subscriptions, function(sub){
	    			$(document).on(sub, instance, instance.onContextEvent);	
	    		});
	    		
	    	};
	    	
	    	/*
	    	 * Post events on instance name
	    	 */
	    	this.postEvent = function()
	    	{
				$(document).trigger(this.name, [arguments[0]]);
	    	};
	    	
	    };
	    
	    _.extend(Raijin, {
	    	create: function( config ) {
	    		return new Raijin( _.extend( {}, config) );
	    	}
	    });
	    
	    return Raijin.create( { exportName:'raijin' } );
});