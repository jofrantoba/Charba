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
package org.pepstock.charba.client.defaults.chart;

import org.pepstock.charba.client.defaults.globals.DefaultElements;
import org.pepstock.charba.client.options.Elements;

/**
 * Defaults for elements option element, based on chart type.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultChartElements extends DefaultElements {

	/**
	 * Creates the object by elements option element instance.
	 * 
	 * @param elements elements option element instance.
	 */
	DefaultChartElements(Elements elements) {
		super(new DefaultChartArc(elements.getArc()), new DefaultChartLine(elements.getLine()), new DefaultChartPoint(elements.getPoint()), new DefaultChartRectangle(elements.getRectangle()));
	}

}
