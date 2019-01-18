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
package org.pepstock.charba.client.callbacks;

import org.pepstock.charba.client.configuration.Axis;
import org.pepstock.charba.client.items.AxisItem;

/**
 * Interface to be implemented which can be used to change parameters in the scale during ticks label conversion.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface AxisTickToLabelConversionCallback {

	/**
	 * Callback that runs before ticks are converted into strings.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item axis item instance
	 */
	void onBeforeTickToLabelConversion(Axis axis, AxisItem item);

	/**
	 * Callback that runs after ticks are converted into strings.
	 * 
	 * @param axis axis instance where this callback as been defined
	 * @param item axis item instance
	 */
	void onAfterTickToLabelConversion(Axis axis, AxisItem item);

}