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

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.ChartType;
import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.Type;
import org.pepstock.charba.client.callbacks.CubicInterpolationModeCallback;
import org.pepstock.charba.client.callbacks.ScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.ArrayStringList;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultOptions;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.DataType;
import org.pepstock.charba.client.enums.SteppedLine;
import org.pepstock.charba.client.items.UndefinedValues;
import org.pepstock.charba.client.options.Scales;

/**
 * The line chart allows a number of properties to be specified for each dataset. These are used to set display properties for a
 * specific dataset.<br>
 * All point* properties can be specified as an array. If these are set to an array value, the first value applies to the first
 * point, the second value to the second point, and so on.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class LineDataset extends LiningDataset implements HasDataPoints {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the cubic interpolation mode function
	private final CallbackProxy<ScriptableFunctions.ProxyStringCallback> cubicInterpolationModeCallbackProxy = JsHelper.get().newCallbackProxy();

	// cubic interpolation mode callback instance
	private CubicInterpolationModeCallback cubicInterpolationModeCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		X_AXIS_ID("xAxisID"),
		Y_AXIS_ID("yAxisID"),
		CUBIC_INTERPOLATION_MODE("cubicInterpolationMode"),
		SHOW_LINE("showLine"),
		CLIP("clip"),
		STEPPED_LINE("steppedLine");

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
	 * Creates a dataset.<br>
	 * It uses the global options has default.
	 */
	public LineDataset() {
		this(ChartType.LINE);
	}

	/**
	 * Creates the dataset using a default.
	 * 
	 * @param defaultValues default options
	 */
	public LineDataset(IsDefaultOptions defaultValues) {
		this(ChartType.LINE, defaultValues);
	}

	/**
	 * Creates the dataset using chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 */
	protected LineDataset(Type type) {
		this(type, null);
	}

	/**
	 * Creates the dataset using a default and chart type related to the dataset.
	 * 
	 * @param type chart type related to the dataset
	 * @param defaultValues default options
	 */
	protected LineDataset(Type type, IsDefaultOptions defaultValues) {
		super(type, defaultValues);
		// -------------------------------
		// -- SET CALLBACKS to PROXIES ---
		// -------------------------------
		// gets value calling callback
		cubicInterpolationModeCallbackProxy.setCallback((contextFunction, context) -> onCubicInterpolationMode(context));
	}

	/**
	 * Sets the ID of the x axis to plot this dataset on.
	 * 
	 * @param xAxisID the ID of the x axis to plot this dataset on.
	 */
	public void setXAxisID(String xAxisID) {
		setValue(Property.X_AXIS_ID, xAxisID);
	}

	/**
	 * Returns the ID of the x axis to plot this dataset on.
	 * 
	 * @return the ID of the x axis to plot this dataset on. Default is
	 *         {@link org.pepstock.charba.client.options.Scales#DEFAULT_X_AXIS_ID}
	 */
	public String getXAxisID() {
		return getValue(Property.X_AXIS_ID, Scales.DEFAULT_X_AXIS_ID);
	}

	/**
	 * Sets the ID of the y axis to plot this dataset on.
	 * 
	 * @param yAxisID the ID of the y axis to plot this dataset on.
	 */
	public void setYAxisID(String yAxisID) {
		setValue(Property.Y_AXIS_ID, yAxisID);
	}

	/**
	 * Returns the ID of the y axis to plot this dataset on.
	 * 
	 * @return the ID of the y axis to plot this dataset on. Default is
	 *         {@link org.pepstock.charba.client.options.Scales#DEFAULT_Y_AXIS_ID}
	 */
	public String getYAxisID() {
		return getValue(Property.Y_AXIS_ID, Scales.DEFAULT_Y_AXIS_ID);
	}

	/**
	 * Sets algorithm used to interpolate a smooth curve from the discrete data points.<br>
	 * The following interpolation modes are supported:<br>
	 * <br>
	 * 
	 * <pre>
	 * 'default'
	 * 'monotone'
	 * </pre>
	 * 
	 * <br>
	 * The 'default' algorithm uses a custom weighted cubic interpolation, which produces pleasant curves for all types of
	 * datasets.<br>
	 * The 'monotone' algorithm is more suited to y = f(x) datasets : it preserves monotonicity (or piecewise monotonicity) of
	 * the dataset being interpolated, and ensures local extremums (if any) stay at input data points.
	 * 
	 * @param mode algorithm used to interpolate a smooth curve from the discrete data points
	 */
	public void setCubicInterpolationMode(CubicInterpolationMode mode) {
		// reset callback
		setCubicInterpolationMode((CubicInterpolationModeCallback) null);
		// stores value
		setValue(Property.CUBIC_INTERPOLATION_MODE, mode);
	}

	/**
	 * Returns algorithm used to interpolate a smooth curve from the discrete data points.
	 * 
	 * @return algorithm used to interpolate a smooth curve from the discrete data points.
	 */
	public CubicInterpolationMode getCubicInterpolationMode() {
		// checks if a callback has been set for this property
		if (getCubicInterpolationModeCallback() == null) {
			return getValue(Property.CUBIC_INTERPOLATION_MODE, CubicInterpolationMode.class, getDefaultValues().getElements().getLine().getCubicInterpolationMode());
		}
		// if here, the property is a callback
		// then returns the default
		return getDefaultValues().getElements().getLine().getCubicInterpolationMode();
	}

	/**
	 * Sets if the line is not drawn for this dataset.
	 * 
	 * @param showLine <code>false</code> if the line is not drawn for this dataset.
	 */
	public void setShowLine(boolean showLine) {
		setValue(Property.SHOW_LINE, showLine);
	}

	/**
	 * Returns if the line is not drawn for this dataset.
	 * 
	 * @return <code>false</code> if the line is not drawn for this dataset.
	 */
	public boolean isShowLine() {
		return getValue(Property.SHOW_LINE, getDefaultValues().isShowLines());
	}

	/**
	 * Sets if the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored.
	 * 
	 * @param line if the line is shown as a stepped line. <code>false</code> is no step interpolation
	 */
	public void setSteppedLine(boolean line) {
		// checks if no stepped line
		if (!line) {
			// sets boolean value instead of string one
			setValue(Property.STEPPED_LINE, false);
		} else {
			// sets value before, equals to true
			setValue(Property.STEPPED_LINE, SteppedLine.BEFORE);
		}
	}

	/**
	 * Sets if the line is shown as a stepped line.<br>
	 * If the steppedLine value is set to anything other than false, lineTension will be ignored.
	 * 
	 * @param line if the line is shown as a stepped line.
	 */
	public void setSteppedLine(SteppedLine line) {
		// checks if no stepped line
		if (SteppedLine.FALSE.equals(line)) {
			// sets boolean value instead of string one
			setValue(Property.STEPPED_LINE, false);
		} else {
			// sets value
			setValue(Property.STEPPED_LINE, line);
		}
	}

	/**
	 * Returns if the line is shown as a stepped line.
	 * 
	 * @return If the line is shown as a stepped line.
	 */
	public SteppedLine getSteppedLine() {
		// checks if value of stepped line is a boolean
		if (ObjectType.BOOLEAN.equals(type(Property.STEPPED_LINE))) {
			return SteppedLine.FALSE;
		} else {
			// otherwise returns the steppedline
			return getValue(Property.STEPPED_LINE, SteppedLine.class, SteppedLine.FALSE);
		}
	}

	/**
	 * Sets how to clip relative to chartArea.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.
	 * 
	 * @param clip positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea
	 */
	public void setClip(double clip) {
		// sets value
		setValue(Property.CLIP, clip);
	}

	/**
	 * Sets how to clip relative to chartArea, by an object which configures clipping per side.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.
	 * 
	 * @param clip object which configures clipping per side
	 */
	public void setClip(Clip clip) {
		// sets value
		setValue(Property.CLIP, clip);
	}

	/**
	 * Returns how to clip relative to chartArea.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.<br>
	 * If the clip was set by a {@link Clip} object, returns {@link UndefinedValues#DOUBLE}.
	 * 
	 * @return positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at
	 *         chartArea.<br>
	 *         If the clip was set by a {@link Clip} object, returns {@link UndefinedValues#DOUBLE}
	 */
	public double getClip() {
		// gets the type stored
		ObjectType type = type(Property.CLIP);
		// checks if previously was set to a clip object
		// therefore NaN
		if (ObjectType.OBJECT.equals(type)) {
			// if object returns NaN
			return UndefinedValues.DOUBLE;
		}
		// gets value as number
		return getValue(Property.CLIP, Defaults.get().getGlobal().getElements().getLine().getBorderWidth() / 2D);
	}

	/**
	 * Returns how to clip relative to chartArea.<br>
	 * Positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at chartArea.<br>
	 * If the clip was NOT set by a {@link Clip} object, returns a {@link Clip} instance with the same values.
	 * 
	 * @return clip positive value allows overflow, negative value clips that many pixels inside chartArea. 0 = clip at
	 *         chartArea.<br>
	 *         If the clip was NOT set by a {@link Clip} object, returns a {@link Clip} instance with the same values.
	 */
	public Clip getClipAsObject() {
		// gets the type stored
		ObjectType type = type(Property.CLIP);
		// checks if previously was set to a number
		// therefore new object with the same values
		if (ObjectType.NUMBER.equals(type)) {
			// new object
			// with the same value
			return new Clip(getClip());
		}
		// creates new value with previous item
		// if there is otherwise an empyt object
		return new Clip(getValue(Property.CLIP));
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param data an array of strings
	 */
	public void setDataString(String... data) {
		setArrayValue(Dataset.Property.DATA, ArrayString.fromOrNull(data));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.STRINGS : DataType.UNKNOWN);
	}

	/**
	 * Sets the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param data a list of strings
	 */
	public void setDataString(List<String> data) {
		setArrayValue(Dataset.Property.DATA, ArrayString.fromOrNull(data));
		// sets data type checking if the key exists
		setValue(Dataset.Property.CHARBA_DATA_TYPE, has(Dataset.Property.DATA) ? DataType.STRINGS : DataType.UNKNOWN);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#STRINGS}.
	 */
	public List<String> getDataString() {
		return getDataString(false);
	}

	/**
	 * Returns the data property of a dataset for a chart is specified as an array of strings. Each point in the data array
	 * corresponds to the label at the same index on the x axis.
	 * 
	 * @param binding if <code>true</code> binds the new array list into container
	 * @return a list of strings or an empty list of strings if the data type is not {@link DataType#STRINGS}.
	 */
	public List<String> getDataString(boolean binding) {
		// checks if is a string data type
		if (has(Dataset.Property.DATA) && DataType.STRINGS.equals(getDataType())) {
			/// returns strings
			ArrayString array = getArrayValue(Dataset.Property.DATA);
			return ArrayListHelper.list(array);
		}
		// checks if wants to bind the array
		if (binding) {
			ArrayStringList result = new ArrayStringList();
			// set value
			setArrayValue(Dataset.Property.DATA, ArrayString.fromOrEmpty(result));
			// sets data type
			setValue(Dataset.Property.CHARBA_DATA_TYPE, DataType.STRINGS);
			// returns list
			return result;
		}
		// returns an empty list
		return new LinkedList<>();
	}

	/**
	 * Returns the border join style callback, if set, otherwise <code>null</code>.
	 * 
	 * @return the border join style callback, if set, otherwise <code>null</code>.
	 */
	public CubicInterpolationModeCallback getCubicInterpolationModeCallback() {
		return cubicInterpolationModeCallback;
	}

	/**
	 * Sets the border join style callback.
	 * 
	 * @param cubicInterpolationModeCallback the border join style callback.
	 */
	public void setCubicInterpolationMode(CubicInterpolationModeCallback cubicInterpolationModeCallback) {
		// sets the callback
		this.cubicInterpolationModeCallback = cubicInterpolationModeCallback;
		// checks if callback is consistent
		if (cubicInterpolationModeCallback != null) {
			// adds the callback proxy function to java script object
			setValue(Property.CUBIC_INTERPOLATION_MODE, cubicInterpolationModeCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			remove(Property.CUBIC_INTERPOLATION_MODE);
		}
	}

	/**
	 * Returns a {@link CubicInterpolationMode} when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @return a object property value, as {@link CubicInterpolationMode}
	 */
	private String onCubicInterpolationMode(ScriptableContext context) {
		// gets value
		CubicInterpolationMode result = ScriptableUtils.getOptionValue(context, cubicInterpolationModeCallback);
		// checks result
		if (result != null) {
			return result.value();
		}
		// default result
		return getDefaultValues().getElements().getLine().getCubicInterpolationMode().value();
	}

}