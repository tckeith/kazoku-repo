requirejs = 
{
		baseUrl: 'scripts/',
        urlArgs: 'v=1.0.0',
        paths:
        {
        	/*Framework*/
        	raijin:			'libraries/raijin-1.0.0',
        	
        	Entity:			'app-utils/Entity',
        	
        	/*dependencies*/
        	bootstrap: 		'libraries/bootstrap-4.0.0-alpha',
        	domReady: 		'libraries/domReady-2.0.1',
        	jquery:			'libraries/jquery-3.2.1',
        	moment: 		'libraries/moment-2.18.1',
        	text: 			'libraries/text-2.0.15',
        	backbone: 		'libraries/backbone-1.3.3',
        	underscore: 	'libraries/underscore-1.8.3',
        	blockui: 		'libraries/blockui-2.70.0',
        	toastr: 		'libraries/toastr-2.1.3',
        	CryptoJS: 		'libraries/cryptoJSMD5-3.1.2',
        		
        	//viewmodels
        		
        	//view
        	TestView:		'views/TestView'
        },
        shim : {
        	bootstrap: {deps: ['jquery']},
        	raijin: {deps: ['jquery', 'underscore']}
		}


}