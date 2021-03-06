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
package org.pepstock.charba.client.data;

import java.util.Date;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;

/**
 * Used for sparse datasets, such as those in scatter charts. Each data point is specified using an object containing x and y
 * properties.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DataPoint extends NativeObjectContainer {

	// default value for X. No private because it is used by time series item
	static final double DEFAULT_X = Double.NaN;
	// default value for R. No private because it is used by time series item
	static final double DEFAULT_R = Double.NaN;
	// default value for Y. No private because it is used by time series item
	static final double DEFAULT_Y = Double.NaN;

	/**
	 * Name of properties of native object.<br>
	 * No private because it is used by time series item
	 */
	enum Property implements Key
	{
		X("x"),
		Y("y"),
		R("r"),
		T("t");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Creates the object with an empty native object.
	 */
	public DataPoint() {
		super();
	}

	/**
	 * Creates the object with a native object passed as argument.
	 * 
	 * @param nativeObject native object which maps a data point
	 */
	DataPoint(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets X value.
	 * 
	 * @param x X value.
	 */
	public void setX(double x) {
		setValue(Property.X, x);
	}

	/**
	 * Returns X value.
	 * 
	 * @return X value.
	 */
	public double getX() {
		return getValue(Property.X, DEFAULT_X);
	}

	/**
	 * Sets Y value.
	 * 
	 * @param y Y value.
	 */
	public void setY(double y) {
		setValue(Property.Y, y);
	}

	/**
	 * Returns Y value.
	 * 
	 * @return Y value.
	 */
	public double getY() {
		return getValue(Property.Y, DEFAULT_Y);
	}

	/**
	 * Sets the bubble radius in pixels (not scaled).<br>
	 * It is not scaled by the chart, it is the raw radius in pixels of the bubble that is drawn on the canvas.
	 * 
	 * @param r the bubble radius in pixels (not scaled).
	 */
	public void setR(double r) {
		setValue(Property.R, r);
	}

	/**
	 * Returns the bubble radius in pixels (not scaled).
	 * 
	 * @return the bubble radius in pixels (not scaled).
	 */
	public double getR() {
		return getValue(Property.R, DEFAULT_R);
	}

	/**
	 * Sets T value, is the date for time series.
	 * 
	 * @param t T value.
	 */
	public void setT(Date t) {
		setValue(Property.T, t);
	}

	/**
	 * Returns T value, is the date for time series.
	 * 
	 * @return T value. <code>null</code> is not set.
	 */
	public Date getT() {
		return getValue(Property.T, (Date) null);
	}

	/**
	 * Sets a custom field to data point.
	 * 
	 * @param key key of java script object to set.
	 * @param value value to set.
	 */
	public void setAttribute(Key key, double value) {
		setValue(key, value);
	}

	/**
	 * Returns a custom field value from data point.
	 * 
	 * @param key key of java script object to get.
	 * @return custom field value from data point. Default is {@link java.lang.Double#NaN}.
	 */
	public double getAttribute(Key key) {
		return getValue(key, Double.NaN);
	}
}