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
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Extends a JavaScript object container for all entities which need the options instance to store the configuration of chart.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class ConfigurationContainer<T extends NativeObjectContainer> extends ChartContainer {

	// options instance
	private T configuration;

	/**
	 * Creates the chart configuration object with the chart instance
	 * 
	 * @param chart chart instance
	 */
	protected ConfigurationContainer(AbstractChart<?, ?> chart) {
		super(chart);
	}

	/**
	 * Creates the chart configuration object with the chart instance and the options
	 * 
	 * @param chart chart instance
	 * @param configuration options instance to store the configuration of chart.
	 */
	public ConfigurationContainer(AbstractChart<?, ?> chart, T configuration) {
		super(chart);
		this.configuration = configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	protected final void setConfiguration(T configuration) {
		this.configuration = configuration;
	}

	/**
	 * Returns the configuration element.
	 * 
	 * @return the configuration element.
	 */
	protected final T getConfiguration() {
		return configuration;
	}

	/**
	 * Returns the JSON representation of the object.
	 * 
	 * @return the JSON representation of the object.
	 */
	public final String toJSON() {
		return configuration.toJSON();
	}

}