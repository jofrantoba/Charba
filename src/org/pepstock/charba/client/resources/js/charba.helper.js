	/*
	    Copyright 2017 Andrea "Stock" Stocchero
	
	    Licensed under the Apache License, Version 2.0 (the "License");
	    you may not use this file except in compliance with the License.
	    You may obtain a copy of the License at
	
		    http://www.apache.org/licenses/LICENSE-2.0
	
	    Unless required by applicable law or agreed to in writing, software
	    distributed under the License is distributed on an "AS IS" BASIS,
	    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	    See the License for the specific language governing permissions and
	    limitations under the License.
	*/
	/*
		JSHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA, because 
		JSINTEROP is not able to address all javascript model.   
	*/
    function CharbaJsHelper() {}
    /*
	 Returns the java script object type of the object.
	  
	 @param obj the object to get type.
	 @return the object type
    */
    CharbaJsHelper.typeOf = function(obj) {
    	return typeof obj;
    }
   /*
	 Returns an undefined.
	 @return undefined
    */
    CharbaJsHelper.undefined = function() {
    	return undefined;
    }
   /*
	 Removes a property from a java script object.
	 
	 @param obj the object on which to remove the property.
	 @param key the string name of the property to remove.
    */
    CharbaJsHelper.remove = function(obj, key) {
    	delete obj[key];
    }
    /*
	 Returns the java script object type of a property.
	  
	 @param obj the object on which to search the property.
	 @param key the string name of the property to test.
	 @return the object type
    */
    CharbaJsHelper.type = function(obj, key) {
    	return typeof obj[key];
    }
    /*
	 This method determines whether the passed property of passed object is an Array.
	  
	 @param obj the object on which to test the property.
	 @param key the string name of the property to test.
	 @return true if the value is an Array; otherwise, false.
    */
    CharbaJsHelper.isArray = function(obj, key) {
	    return Array.isArray(obj[key]);
    }    
    /*
	 Returns a property of java script object as string.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return string value
    */
    CharbaJsHelper.propertyAsString = function(obj, key) {
    	return obj[key];
    }
    /*
	 Returns a property of java script object as double.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return double value
    */
    CharbaJsHelper.propertyAsDouble = function(obj, key) {
    	return obj[key];
    }
    /*
	 Returns a property of java script object as integer.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return integer value
    */
    CharbaJsHelper.propertyAsInt = function(obj, key) {
    	return obj[key];
    }  
    /*
	 Sets the line dash pattern used when stroking lines. It uses an array of values that specify alternating lengths of lines
	 and gaps which describe the pattern.
	 
	 @param context context of canvas
	 @param values array of values that specify alternating lengths of lines and gaps which describe the pattern
    */
    CharbaJsHelper.setLineDash = function(context, values) {
    	context.setLineDash(values);
    }
    /*
	 Sets the line dash offset, or "phase."
	 
	 @param context context of canvas
	 @param offset the line dash offset, or "phase."
    */
    CharbaJsHelper.setLineDashOffset = function(context, offset) {
    	context.lineDashOffset = offset;
    }
    /*
	 Creates new proxy for callback which will pass "this" environment of java script as first argument of callback
	 method.
	  
	 @return new proxy for callback.
    */
    CharbaJsHelper.newCallbackProxy = function() {
    	/*
    		Creates an object with 2 properties.
    		CALLBACK: contains user callback implementation which must be called
    		PROXY: contains a function which can call CALLBACk passing "this" 
    	 */
    	var obj = new Object();
		// CALLBACK
    	obj.callback = null;
    	// PROXY
    	obj.proxy = function() {
    		// checks if callback is a function	
        	if (obj.callback != null && typeof obj.callback === 'function'){
        		// creates arguments for callbacks adding the "this" 
				var args = Array.of(this).concat(Array.prototype.slice.call(arguments));
				// calls CALLBACK
				var result = obj.callback.apply(this, args);
				if (result === null){
					// do nothing console.log("null");
				} else if (result === undefined){
					//console.log("undefined");
				} else {
					//console.log(result);
					return result;
				}
    		} else {
    			//console.log("No caller");
    		}
		};
    	return obj;
    }
    /*
		JSControllerHelper is an object with a set of static methods used as utility
		and needed to improve JSINTEROP adoption for CHARBA controllers implementation.   
	*/
    function CharbaJsControllerHelper() {}
    /*
     Register the controller which does not extend any existing one.
	  
	 @param controllerType controller type
	 @param instance controller java script instance
    */
    CharbaJsControllerHelper.register = function(controllerType, instance) {
    	Chart.controllers[controllerType] = Chart.DatasetController.extend(instance);
    }
    /*
	 Extends an existing chart with a controller implementation.
	  
	 @param controllerType controller type
	 @param chartType type of extended chart
	 @param instance controller java script instance    
    */
    CharbaJsControllerHelper.extend = function(controllerType, chartType, instance) {
		Chart.defaults[controllerType] = Chart.defaults[chartType];
		Chart.defaults.global.datasets[controllerType] = Chart.defaults.global.datasets[chartType];
		Chart.controllers[controllerType] = Chart.controllers[chartType].extend(instance);
    }
    /*
	 Invokes the default "initialize" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param datasetIndex dataset index
    */
    CharbaJsControllerHelper.initialize = function(controllerType, context, datasetIndex) {
    	Chart.controllers[controllerType].prototype.initialize.call(context, context.chart, datasetIndex);
    }
    /*
	 Invokes the default "AddElements" method.
	  
	 @param controllerType controller type
	 @param context context of controller
    */
    CharbaJsControllerHelper.addElements = function(controllerType, context) {
       	Chart.controllers[controllerType].prototype.addElements.call(context);
    }
    /*
	 Invokes the default "addElementAndReset" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param index dataset index
    */
    CharbaJsControllerHelper.addElementAndReset = function(controllerType, context, index) {
    	Chart.controllers[controllerType].prototype.addElementAndReset.call(context, index);
    }
    /*
	 Invokes the default "draw" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param ease if specified, this number represents how far to transition elements.
    */
    CharbaJsControllerHelper.draw = function(controllerType, context, ease) {
    	Chart.controllers[controllerType].prototype.draw.call(context, ease);
    }
    /*
	 Invokes the default "removeHoverStyle" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param element element to be remove.
    */
    CharbaJsControllerHelper.removeHoverStyle = function(controllerType, context, element) {
    	Chart.controllers[controllerType].prototype.removeHoverStyle.call(context, element);
    }
    /*
	 Invokes the default "setHoverStyle" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param element element to be set.
    */
    CharbaJsControllerHelper.setHoverStyle = function(controllerType, context, element) {
    	Chart.controllers[controllerType].prototype.setHoverStyle.call(context, element);
    }
    /*
	 Invokes the default "update" method.
	  
	 @param controllerType controller type
	 @param context context of controller
	 @param reset if true, put the elements into a reset state so they can animate to their final values
    */
    CharbaJsControllerHelper.update = function(controllerType, context, reset) {
    	Chart.controllers[controllerType].prototype.update.call(context, reset);
    }
    /*
		JSWindowHelper is an object with a set of static methods used as utility
		and needed to act on window java script object.   
	*/
    function CharbaJsWindowHelper() {}
    /*
     CSS media queries allow changing styles when printing a page. The CSS applied from these media queries may cause charts
	 to need to resize. However, the resize won't happen automatically. To support resizing charts when printing, one needs to
	 hook the "onbeforeprint" event and manually trigger resizing of each chart.
    */
    CharbaJsWindowHelper.enableResizeOnBeforePrint = function() {
    	window.onbeforeprint = function (event) {
 			for (var id in Chart.instances) {
    			Chart.instances[id].resize();
  			}
		}
    }
    /*
	 Returns an array of strings with element attributes.
	  
	 @param element DOM element to scan
	 @return an array of strings with element attributes
    */
    CharbaJsWindowHelper.elementAttributes = function(element) {
    	var result = new Array();
    	// First, let's verify that the paragraph has some attributes    
     	if (element.hasAttributes()) {
           var attrs = element.attributes;
	       for(var i = 0; i < attrs.length; i++) {
	          result[i] = attrs[i].name + "='" + attrs[i].value +"'";
	       }
	    }
    	return result;
    }  
    /*
		JSPositionerHelper is an object with a set of static methods used as utility
		and needed to add custom positioner on tooltips.   
	*/
    function CharbaJsPositionerHelper() {}
    /*
     Registers a custom postioner for tooltips into CHART.JS.

	 @param name name of new position to set into tooltip config
	 @param module function to invoke to get control
    */
    CharbaJsPositionerHelper.register = function(name, module) {
    	if (module != null && typeof module === 'function'){
	    	Chart.Tooltip.positioners[name] = module;
    	}
    }
    /*
     Unregisters a custom postioner for tooltips from CHART.JS.

	 @param name name of new position to set into tooltip config
    */
    CharbaJsPositionerHelper.unregister = function(name) {
    	if (Chart.Tooltip.positioners[name] != 'undefined'){
 		    delete Chart.Tooltip.positioners[name];
    	}
    }
    /*
	 Invokes an existing positioner to get the point.
	  
	 @param name name of position to be invoked
	 @param context function context of javascript call
	 @param elements datasets items
	 @param eventPoint the point on the canvas where the event occurred
	 @return the point calculated by positioner or <code>null</code> if positioner does not exist
    */
    CharbaJsPositionerHelper.invoke = function(name, context, elements, eventPoint) {
    	if (Chart.Tooltip.positioners[name] != 'undefined'){
    		return Chart.Tooltip.positioners[name].apply(context, Array.of(elements, eventPoint));
    	}
    	return null;
    }
    /*
		JSCallbacksHelper is an object with a set of static methods used as utility
		and needed to act on CHART.JS default callbacks.   
	*/
    function CharbaJsCallbacksHelper() {}
    /*
     Invokes the default generate legend callbacks from CHART.JS.

	 @param chart chart instance
	 @param options chart options where generate legend callback is stored
	 @return HTML legend
    */
    CharbaJsCallbacksHelper.generateDefaultLegend = function(chart, options) {
    	if (options != null && typeof options.legendCallback === 'function'){
    		return options.legendCallback.call(chart, chart);
    	}
    	return null;
    }
    /*
     Invokes the default generate labels callbacks from CHART.JS.

	 @param chart chart instance
	 @param options chart options where generate legend callback is stored
	 @return an array of labels
    */
    CharbaJsCallbacksHelper.generateDefaultLabels = function(chart, options) {
    	if (options != null && typeof options.legend === 'object' && typeof options.legend.labels === 'object' && typeof options.legend.labels.generateLabels === 'function'){
    		return options.legend.labels.generateLabels.call(chart, chart);
     	}
    	return null;
    }
    /*
	 Invokes the legend event callbacks, provided out of the box by CHART.JS.
	  
	 @param options chart options, generated merging all defaults.
	 @param key the key of options which should have the event callback
	 @param chart chart instance, used as function context
	 @param event native event from user interface
	 @param item legend item native  
    */
    CharbaJsCallbacksHelper.invokeDefaultLegendEvent = function(options, key, chart, event, item) {
    	if (options != null && typeof options.legend === 'object' && typeof options.legend[key] === 'function'){
    		options.legend[key].call(chart, event, item);
    	}
    }
    /*
	 Invokes the chart event callbacks, provided out of the box by CHART.JS.
	  
	 @param options chart options, generated merging all defaults.
	 @param key the key of options which should have the event callback
	 @param chart chart instance, used as function context
	 @param event native event from user interface
	 @param items array of datasets native objects  
    */
    CharbaJsCallbacksHelper.invokeDefaultChartEvent = function(options, key, chart, event, items) {
    	if (options != null && typeof options[key] === 'function'){
    		options[key].call(chart, event, items);
    	}
    }
    /*
		JsHtmlLegendBuilderHelpers is an object with a set of static methods used as utility
		and needed when HtmlLegendBuilder plugin has been activated.   
	*/
    function CharbaJsHtmlLegendBuilderHelper() {}
    /*
	 Adds event listener to a DOM element.
	  
	 @param event event name to add
	 @param element DOM element to scan
	 @param proxy charba callback proxy
    */
    CharbaJsHtmlLegendBuilderHelper.addEventListener = function(event, element, proxy) {
    	element.addEventListener(event, proxy, false);
    } 
    /*
	 Removes event listener to a DOM element.
	  
	 @param event event name to remove
	 @param element DOM element to scan
	 @param proxy charba callback proxy
    */
    CharbaJsHtmlLegendBuilderHelper.removeEventListener = function(event, element, proxy) {
    	element.removeEventListener(event, proxy);
    }
    /*
		JsZoomHelpers is an object with a set of static methods used as utility
		and needed when ZOOM plugin has been activated.   
	*/
    function CharbaJsZoomHelper() {} 
    /*
	 Invokes the chart reset zoom function if exists.
	  
	 @param chart chart instance
    */
    CharbaJsZoomHelper.resetZoom = function(chart) {
    	if (chart != null && typeof chart.resetZoom === 'function'){
    		chart.resetZoom.call(chart);
    	}
    }
    /*
		JsItemsHelpers is an object with a set of static methods used as utility
		and needed to act with CHARBA items.   
	*/
    function CharbaJsItemsHelper() {} 
    /*
	 Returns true if the property into native object is a CanvasPattern.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return true if the property into native object is a CanvasPattern
    */
    CharbaJsItemsHelper.isCanvasPattern = function(obj, key) {
    	return obj[key] instanceof CanvasPattern;
    } 
    /*
	 Returns true if the property into native object is a CanvasGradient.
	  
	 @param obj the object on which to define the property.
	 @param key the string name of the property to be defined or modified..
	 @return true if the property into native object is a CanvasGradient
    */
    CharbaJsItemsHelper.isCanvasGradient = function(obj, key) {
    	return obj[key] instanceof CanvasGradient;
    } 
    /*
	 Returns a chart native event from CHART.JS event.
	  
	 @param obj native event instance
	 @param key key of java script object
	 @return a chart native event
    */
    CharbaJsItemsHelper.nativeEvent = function(obj, key) {
    	return obj[key];
    }      
    