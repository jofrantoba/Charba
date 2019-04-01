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
package org.pepstock.charba.client.impl.plugins.enums;

import org.pepstock.charba.client.commons.Key;

/**
 * The elements of the chart in scope of "cursorpointer" plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum PointerElement implements Key
{

	/**
	 * Changes the cursor when over to a dataset.
	 */
	dataset,
	/**
	 * Changes the cursor when over to the title.
	 */
	title,
	/**
	 * Changes the cursor when over to an axes.
	 */
	axes,
	/**
	 * Changes the cursor when over to the legend.
	 */
	legend;
}