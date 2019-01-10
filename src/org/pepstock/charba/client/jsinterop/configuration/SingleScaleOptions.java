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
package org.pepstock.charba.client.jsinterop.configuration;

import org.pepstock.charba.client.jsinterop.AbstractChart;
import org.pepstock.charba.client.jsinterop.ChartOptions;

/**
 * Configuration of chart with only 1 scale.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public abstract class SingleScaleOptions extends ConfigurationOptions{

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options 
	 */
	public SingleScaleOptions(AbstractChart<?, ?> chart, ChartOptions defaultValues) {
		super(chart, defaultValues);
	}

	/**
	 * Sets the single axis of chart.
	 * 
	 * @param axis the axis.
	 */
	public void setAxis(Axis axis) {
		getConfiguration().setScale(axis.getConfiguration());
	}

}