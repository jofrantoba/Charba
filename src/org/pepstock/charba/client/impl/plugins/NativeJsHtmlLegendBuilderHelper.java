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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.commons.CallbackProxy.Proxy;
import org.pepstock.charba.client.commons.NativeName;

import com.google.gwt.dom.client.Element;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is a singleton wrapper for Java native object which is wrapping a CHARBA java script object implementation with some
 * utilities to invoke CHART.JS callbacks, provided out of the box, the default one.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, name = NativeName.JSHTMLLEGENDBUILDERHELPER, namespace = JsPackage.GLOBAL)
final class NativeJsHtmlLegendBuilderHelper {

	/**
	 * To avoid any instantiation
	 */
	NativeJsHtmlLegendBuilderHelper() {
		// do nothing
	}

	static native void addEventListener(String event, Element element, Proxy proxy);
	
	static native void removeEventListener(String event, Element element, Proxy proxy);

}