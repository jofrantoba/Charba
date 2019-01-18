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
package org.pepstock.charba.client.configuration;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.ChartOptions;

/**
 * Configuration of chart with multiple scales.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
abstract class MultiScalesOptions extends ConfigurationOptions {

	private final Scales scales;

	/**
	 * Builds the object storing the chart instance and default values.
	 * 
	 * @param chart chart instance
	 * @param defaultValues defaults options
	 */
	protected MultiScalesOptions(AbstractChart<?, ?> chart, ChartOptions defaultValues) {
		super(chart, defaultValues);
		// new scales creation
		scales = new Scales(chart, getConfiguration());
	}

	/**
	 * @return the scales
	 */
	public Scales getScales() {
		return scales;
	}

}