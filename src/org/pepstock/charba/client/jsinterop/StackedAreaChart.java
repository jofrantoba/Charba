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

import org.pepstock.charba.client.jsinterop.configuration.StackedOptions;
import org.pepstock.charba.client.jsinterop.data.StackedAreaDataset;

/**
 * STACKED AREA chart implementation.<br>
 * A stacked area chart is a way of plotting data points on a line.<br>
 * Often, it is used to show trend data, or the comparison of two data sets.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public class StackedAreaChart extends AbstractChart<StackedOptions, StackedAreaDataset> {

	private final StackedOptions options;

	/**
	 * Builds the object.
	 */
	public StackedAreaChart() {
		options = new StackedOptions(this, getChartOptions(), true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getType()
	 */
	@Override
	public Type getType() {
		return ChartType.line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#getOptions()
	 */
	@Override
	public StackedOptions getOptions() {
		return options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.IsChart#newDataset()
	 */
	@Override
	public StackedAreaDataset newDataset() {
		return new StackedAreaDataset();
	}

}