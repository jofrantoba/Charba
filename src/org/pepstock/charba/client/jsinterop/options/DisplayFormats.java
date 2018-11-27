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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.TimeUnit;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

/**
 * The following display formats are used to configure how different time units are formed into strings for the axis tick marks.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DisplayFormats extends AbstractModel<Time, Void> {

	/**
	 * Builds the object storing the chart instance and the axis which this grid lines belongs to.
	 * 
	 * @param chart chart instance.
	 * @param axis axis which this grid lines belongs to.
	 */
	DisplayFormats(Time time, Key childKey, NativeObject nativeObject) {
		super(time, childKey, null, nativeObject);
	}
	
	/**
	 * Sets the display formats are used to configure how different time units are formed into strings for the axis tick marks.
	 * @param unit time unit.
	 * @param format display format
	 * @see org.pepstock.charba.client.enums.TimeUnit 
	 */
	public void setDisplayFormat(TimeUnit unit, String format){
		setValue(unit, format);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the display formats are used to configure how different time units are formed into strings for the axis tick marks.
	 * @param unit time unit.
	 * @return display format
	 */
	public String getDisplayFormat(TimeUnit unit){
		// returns the configuration creating a key.
		return getValue(unit, unit.getDefaultFormat());
	}
}