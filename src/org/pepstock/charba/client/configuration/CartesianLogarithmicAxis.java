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
import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.CartesianAxisType;

/**
 * This object is used to map defined axis as logarithmic.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public class CartesianLogarithmicAxis extends CartesianAxis<CartesianLogarithmicTick> {

	private final CartesianLogarithmicTick ticks;

	/**
	 * Builds the object storing the chart instance. Axis type is Y by default.
	 * 
	 * @param chart chart instance
	 */
	public CartesianLogarithmicAxis(AbstractChart<?, ?> chart) {
		this(chart, CartesianAxisType.y);
	}

	/**
	 * Builds the object storing the chart instance and axis type.
	 * 
	 * @param chart chart instance
	 * @param cartesianType cartesian axis type.
	 */
	public CartesianLogarithmicAxis(AbstractChart<?, ?> chart, CartesianAxisType cartesianType) {
		super(chart, cartesianType);
		// sets axis type
		super.setType(AxisType.logarithmic);
		// creates the ticks instance
		this.ticks = new CartesianLogarithmicTick(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.jsinterop.configuration.scales.CartesianAxis#getTicks()
	 */
	@Override
	public CartesianLogarithmicTick getTicks() {
		return ticks;
	}

}