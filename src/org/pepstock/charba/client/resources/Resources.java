/**
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
package org.pepstock.charba.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle to reference CHART.JS and other java script codes, always needed to CHARBA.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface Resources extends ClientBundle {

	// static reference of this resource
	public static final Resources INSTANCE = GWT.create(Resources.class);

	/**
	 * Contains text representation of native chart.js code
	 * 
	 * @return chart.js code
	 */
	@Source("js/chart.bundle.min.js")
	TextResource chartJsSource();

	/**
	 * This java script with a set of static methods used as utility and needed to improve JSINTEROP adoption for CHARBA,
	 * because JSINTEROP is not able to address all java script model.
	 * 
	 * @return charba java script code.
	 */
	@Source("js/charba.helper.js")
	TextResource charbaHelper();

	/**
	 * This file contains two functions, JSON.decycle and JSON.retrocycle, which make it possible to encode cyclical structures
	 * and dags in JSON, and to then recover them. This is a capability that is not provided by ES5.<br>
	 * JSONPath is used to represent the links.<br>
	 * See https://github.com/douglascrockford/JSON-js/blob/master/cycle.js.
	 * 
	 * @return cycle2 java script code.
	 */
	@Source("js/json.cycle.js")
	TextResource jsonCycle();

}