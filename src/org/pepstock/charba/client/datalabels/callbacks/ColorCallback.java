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
package org.pepstock.charba.client.datalabels.callbacks;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.colors.Gradient;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.colors.Pattern;
import org.pepstock.charba.client.datalabels.Context;

import com.google.gwt.canvas.dom.client.CanvasGradient;
import com.google.gwt.canvas.dom.client.CanvasPattern;

/**
 * Callback interface of DATALABELS plugin to set <code>color</code> property at runtime, using the chart instance and the
 * plugin context.
 * 
 * @author Andrea "Stock" Stocchero
 *
 * @param <T> type of result of callback. Could be a string (as color), color, pattern or gradient instance
 * @see IsColor
 * @see Pattern
 * @see Gradient
 * @see CanvasPattern
 * @see CanvasGradient
 */
public interface ColorCallback<T> {

	/**
	 * Returns the <code>color</code> property at runtime, using the chart instance and the plugin context.
	 * 
	 * @param chart chart instance
	 * @param context DATALABELS plugin context instance
	 * @return the <code>color</code> value to be applied. Could be a string (as color), color, pattern or gradient instance
	 * @see IsColor
	 * @see Pattern
	 * @see Gradient
	 * @see CanvasPattern
	 * @see CanvasGradient
	 */
	T color(AbstractChart<?, ?> chart, Context context);

}