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
package org.pepstock.charba.client.jsinterop;

import org.pepstock.charba.client.jsinterop.commons.Key;

/**
 * Interface to map the type and scale type of a chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface Type extends Key {

	/**
	 * Returns the scale type of the chart.
	 * 
	 * @return the scale type of the chart.
	 */
	ScaleType scaleType();

}