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
package org.pepstock.charba.client;

import org.pepstock.charba.client.commons.NativeName;
import org.pepstock.charba.client.commons.NativeObject;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * This is the java script native object which is the helpers utility of CHART.JS.<br>
 * It maps the java script object <code>chart.helpers</code>.
 * 
 * @author Andrea "Stock" Stocchero
 */
@JsType(isNative = true, name = NativeName.OBJECT, namespace = JsPackage.GLOBAL)
final class NativeHelpers {

	/**
	 * Recursively deep copies source properties into target only if not defined in target.<br>
	 * IMPORTANT: target is not cloned and will be updated with source properties.
	 * 
	 * @param target the target object in which all sources are merged into.
	 * @param source object to merge into target.
	 * @returns the target object.
	 */
	@JsMethod
	native NativeObject mergeIf(NativeObject target, NativeObject source);

	/**
	 * Returns a deep copy of source without keeping references on objects and arrays.
	 * 
	 * @param source the value to clone.
	 * @returns a clone of source object
	 */
	@JsMethod
	native NativeObject clone(NativeObject target);

}
