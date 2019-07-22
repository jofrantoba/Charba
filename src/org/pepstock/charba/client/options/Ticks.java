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
package org.pepstock.charba.client.options;

import java.util.Date;
import java.util.List;

import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayString;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.defaults.IsDefaultTicks;
import org.pepstock.charba.client.enums.TickSource;

import com.google.gwt.core.client.JsDate;

/**
 * All configuration for ticks of a chart.<br>
 * It defines options for the tick marks that are generated by the axis.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Ticks extends AbstractTick<Scale, IsDefaultTicks> implements IsDefaultTicks {

	private final TickMinor minor;

	private final TickMajor major;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		MINOR("minor"),
		MAJOR("major"),
		DISPLAY("display"),
		REVERSE("reverse"),
		AUTO_SKIP("autoSkip"),
		AUTO_SKIP_PADDING("autoSkipPadding"),
		LABEL_OFFSET("labelOffset"),
		MAX_ROTATION("maxRotation"),
		MIN_ROTATION("minRotation"),
		MIRROR("mirror"),
		PADDING("padding"),
		BEGIN_AT_ZERO("beginAtZero"),
		MIN("min"),
		MAX("max"),
		MAX_TICKS_LIMIT("maxTicksLimit"),
		STEP_SIZE("stepSize"),
		SUGGESTED_MAX("suggestedMax"),
		SUGGESTED_MIN("suggestedMin"),
		BACKDROP_COLOR("backdropColor"),
		BACKDROP_PADDING_X("backdropPaddingX"),
		BACKDROP_PADDING_Y("backdropPaddingY"),
		SHOW_LABEL_BACKDROP("showLabelBackdrop"),
		LABELS("labels"),
		SOURCE("source"),
		PRECISION("precision");

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
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param scale scale/axis of object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Ticks(Scale scale, Key childKey, IsDefaultTicks defaultValues, NativeObject nativeObject) {
		super(scale, childKey, defaultValues, nativeObject);
		// gets sub elements
		minor = new TickMinor(this, Property.MINOR, getDefaultValues().getMinor(), getValue(Property.MINOR));
		major = new TickMajor(this, Property.MAJOR, getDefaultValues().getMajor(), getValue(Property.MAJOR));
	}

	/**
	 * Returns the minor tick element.
	 * 
	 * @return the minor
	 */
	public TickMinor getMinor() {
		return minor;
	}

	/**
	 * Returns the major element.
	 * 
	 * @return the major
	 */
	public TickMajor getMajor() {
		return major;
	}

	/**
	 * If <code>true</code>, scale will include 0 if it is not already included.
	 * 
	 * @param beginAtZero if <code>true</code>, scale will include 0 if it is not already included.
	 */
	public void setBeginAtZero(boolean beginAtZero) {
		setValue(Property.BEGIN_AT_ZERO, beginAtZero);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, scale will include 0 if it is not already included.
	 * 
	 * @return if <code>true</code>, scale will include 0 if it is not already included.
	 */
	public boolean isBeginAtZero() {
		return getValue(Property.BEGIN_AT_ZERO, getDefaultValues().isBeginAtZero());
	}

	/**
	 * If <code>true</code>, show tick marks.
	 * 
	 * @param display if <code>true</code>, show tick marks.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, show tick marks.
	 * 
	 * @return if <code>true</code>, show tick marks.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the reverses order of tick labels.
	 * 
	 * @param reverse reverses order of tick labels.
	 */
	public void setReverse(boolean reverse) {
		setValue(Property.REVERSE, reverse);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the reverses order of tick labels.
	 * 
	 * @return if <code>true</code> reverses order of tick labels.
	 */
	public boolean isReverse() {
		return getValue(Property.REVERSE, getDefaultValues().isReverse());
	}

	/**
	 * If <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it
	 * off to show all labels no matter what
	 * 
	 * @param autoSkip if <code>true</code>, automatically calculates how many labels that can be shown and hides labels
	 *            accordingly. Turn it off to show all labels no matter what
	 */
	public void setAutoSkip(boolean autoSkip) {
		setValue(Property.AUTO_SKIP, autoSkip);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly. Turn it
	 * off to show all labels no matter what
	 * 
	 * @return if <code>true</code>, automatically calculates how many labels that can be shown and hides labels accordingly.
	 *         Turn it off to show all labels no matter what.
	 */
	public boolean isAutoSkip() {
		return getValue(Property.AUTO_SKIP, getDefaultValues().isAutoSkip());
	}

	/**
	 * Sets the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 * scales.
	 * 
	 * @param autoSkipPadding padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable
	 *            to horizontal scales.
	 */
	public void setAutoSkipPadding(int autoSkipPadding) {
		setValue(Property.AUTO_SKIP_PADDING, autoSkipPadding);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to
	 * horizontal scales.
	 * 
	 * @return padding between the ticks on the horizontal axis when autoSkip is enabled. Note: Only applicable to horizontal
	 *         scales.
	 */
	public int getAutoSkipPadding() {
		return getValue(Property.AUTO_SKIP_PADDING, getDefaultValues().getAutoSkipPadding());
	}

	/**
	 * Sets the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis, and
	 * the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @param labelOffset the distance in pixels to offset the label from the center point of the tick (in the y direction for
	 *            the x axis, and the x direction for the y axis)
	 */
	public void setLabelOffset(int labelOffset) {
		setValue(Property.LABEL_OFFSET, labelOffset);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis,
	 * and the x direction for the y axis).<br>
	 * Note: this can cause labels at the edges to be cropped by the edge of the canvas.
	 * 
	 * @return the distance in pixels to offset the label from the center point of the tick (in the y direction for the x axis,
	 *         and the x direction for the y axis).
	 */
	public int getLabelOffset() {
		return getValue(Property.LABEL_OFFSET, getDefaultValues().getLabelOffset());
	}

	/**
	 * Sets the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 * Note: Only applicable to horizontal scales.
	 * 
	 * @param maxRotation maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 *            necessary. Note: Only applicable to horizontal scales.
	 */
	public void setMaxRotation(int maxRotation) {
		setValue(Property.MAX_ROTATION, maxRotation);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until
	 * necessary. Note: Only applicable to horizontal scales.
	 * 
	 * @return maximum rotation for tick labels when rotating to condense labels. Note: Rotation doesn't occur until necessary.
	 *         Note: Only applicable to horizontal scales.
	 */
	public int getMaxRotation() {
		return getValue(Property.MAX_ROTATION, getDefaultValues().getMaxRotation());
	}

	/**
	 * Sets the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @param minRotation minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public void setMinRotation(int minRotation) {
		setValue(Property.MIN_ROTATION, minRotation);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 * 
	 * @return minimum rotation for tick labels. Note: Only applicable to horizontal scales.
	 */
	public int getMinRotation() {
		return getValue(Property.MIN_ROTATION, getDefaultValues().getMinRotation());
	}

	/**
	 * Sets the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 * to vertical scales.
	 * 
	 * @param mirror flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 *            applicable to vertical scales.
	 */
	public void setMirror(boolean mirror) {
		setValue(Property.MIRROR, mirror);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only
	 * applicable to vertical scales.
	 * 
	 * @return flips tick labels around axis, displaying the labels inside the chart instead of outside. Note: Only applicable
	 *         to vertical scales.
	 */
	public boolean isMirror() {
		return getValue(Property.MIRROR, getDefaultValues().isMirror());
	}

	/**
	 * Sets the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @param padding padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal
	 *            (X) direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	public void setPadding(int padding) {
		setValue(Property.PADDING, padding);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 * direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 * 
	 * @return padding between the tick label and the axis. When set on a vertical axis, this applies in the horizontal (X)
	 *         direction. When set on a horizontal axis, this applies in the vertical (Y) direction.
	 */
	public int getPadding() {
		return getValue(Property.PADDING, getDefaultValues().getPadding());
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(double min) {
		setValue(Property.MIN, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public double getMin() {
		return getValue(Property.MIN, getDefaultValues().getMin());
	}
	
	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @param min If defined, this will override the data minimum.
	 */
	public void setMin(Date min) {
		// checks if consistent
		if (min != null) {
			// sets min using the epoch of date
			setMin(min.getTime());
		} else {
			// if here, removes property
			removeIfExists(Property.MIN);
		}

	}

	/**
	 * If defined, this will override the data minimum.
	 * 
	 * @return If defined, this will override the data minimum.
	 */
	public Date getMinAsDate() {
		// checks if there is the property
		if (has(Property.MIN)) {
			// gets the value, getting the max with 0
			double value = Math.max(getMin(), 0);
			// transforms as date
			return new Date((long) JsDate.create(value).getTime());
		}
		// if here, property is missing
		// then returns default (null)
		return null;
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(double max) {
		setValue(Property.MAX, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	public double getMax() {
		return getValue(Property.MAX, getDefaultValues().getMax());
	}
	
	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @param max if defined, this will override the data maximum.
	 */
	public void setMax(Date max) {
		// checks if consistent
		if (max != null) {
			// sets max using the epoch of date
			setMax(max.getTime());
		} else {
			// if here, removes property
			removeIfExists(Property.MAX);
		}
	}

	/**
	 * If defined, this will override the data maximum.
	 * 
	 * @return if defined, this will override the data maximum.
	 */
	public Date getMaxAsDate() {
		// checks if there is the property
		if (has(Property.MIN)) {
			// gets the value, getting the max with 0
			double value = Math.max(getMax(), 0);
			// transforms as date
			return new Date((long) JsDate.create(value).getTime());
		}
		// if here, property is missing
		// then returns default (null)
		return null;
	}

	/**
	 * Sets the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @param min the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public void setMin(String min) {
		setValue(Property.MIN, min);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined minimum number for the scale, overrides minimum value from data.
	 * 
	 * @return the user defined minimum number for the scale, overrides minimum value from data.
	 */
	public String getMinAsString() {
		return getValue(Property.MIN, String.valueOf(getDefaultValues().getMin()));
	}

	/**
	 * Sets the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @param max user defined maximum number for the scale, overrides maximum value from data.
	 */
	public void setMax(String max) {
		setValue(Property.MAX, max);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined maximum number for the scale, overrides maximum value from data.
	 * 
	 * @return user defined maximum number for the scale, overrides maximum value from data.
	 */
	public String getMaxAsString() {
		return getValue(Property.MAX, String.valueOf(getDefaultValues().getMax()));
	}

	/**
	 * Sets the maximum number of ticks and gridlines to show.
	 * 
	 * @param maxTicksLimit maximum number of ticks and gridlines to show.
	 */
	public void setMaxTicksLimit(int maxTicksLimit) {
		setValue(Property.MAX_TICKS_LIMIT, maxTicksLimit);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the maximum number of ticks and gridlines to show.
	 * 
	 * @return maximum number of ticks and gridlines to show.
	 */
	public int getMaxTicksLimit() {
		return getValue(Property.MAX_TICKS_LIMIT, getDefaultValues().getMaxTicksLimit());
	}

	/**
	 * Sets the user defined fixed step size for the scale.
	 * 
	 * @param stepSize user defined fixed step size for the scale.
	 */
	public void setStepSize(double stepSize) {
		setValue(Property.STEP_SIZE, stepSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the user defined fixed step size for the scale.
	 * 
	 * @return user defined fixed step size for the scale.
	 */
	public double getStepSize() {
		return getValue(Property.STEP_SIZE, getDefaultValues().getStepSize());
	}

	/**
	 * Sets the adjustment used when calculating the maximum data value.
	 * 
	 * @param suggestedMax adjustment used when calculating the maximum data value.
	 */
	public void setSuggestedMax(double suggestedMax) {
		setValue(Property.SUGGESTED_MAX, suggestedMax);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the maximum data value.
	 * 
	 * @return adjustment used when calculating the maximum data value.
	 */
	public double getSuggestedMax() {
		return getValue(Property.SUGGESTED_MAX, getDefaultValues().getSuggestedMax());
	}

	/**
	 * Sets the adjustment used when calculating the minimum data value.
	 * 
	 * @param suggestedMin adjustment used when calculating the minimum data value.
	 */
	public void setSuggestedMin(double suggestedMin) {
		setValue(Property.SUGGESTED_MIN, suggestedMin);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the adjustment used when calculating the minimum data value.
	 * 
	 * @return adjustment used when calculating the minimum data value.
	 */
	public double getSuggestedMin() {
		return getValue(Property.SUGGESTED_MIN, getDefaultValues().getSuggestedMin());
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(IsColor backdropColor) {
		setBackdropColor(checkValue(backdropColor));
	}

	/**
	 * Sets the color of label backdrops.
	 * 
	 * @param backdropColor color of label backdrops.
	 */
	public void setBackdropColor(String backdropColor) {
		setValue(Property.BACKDROP_COLOR, backdropColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public String getBackdropColorAsString() {
		return getValue(Property.BACKDROP_COLOR, getDefaultValues().getBackdropColorAsString());
	}

	/**
	 * Returns the color of label backdrops.
	 * 
	 * @return color of label backdrops.
	 */
	public IsColor getBackdropColor() {
		return ColorBuilder.parse(getBackdropColorAsString());
	}

	/**
	 * Sets the horizontal padding of label backdrop.
	 * 
	 * @param backdropPaddingX horizontal padding of label backdrop.
	 */
	public void setBackdropPaddingX(int backdropPaddingX) {
		setValue(Property.BACKDROP_PADDING_X, backdropPaddingX);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the horizontal padding of label backdrop.
	 * 
	 * @return horizontal padding of label backdrop.
	 */
	public int getBackdropPaddingX() {
		return getValue(Property.BACKDROP_PADDING_X, getDefaultValues().getBackdropPaddingX());
	}

	/**
	 * Sets the vertical padding of label backdrop.
	 * 
	 * @param backdropPaddingY vertical padding of label backdrop.
	 */
	public void setBackdropPaddingY(int backdropPaddingY) {
		setValue(Property.BACKDROP_PADDING_Y, backdropPaddingY);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the vertical padding of label backdrop.
	 * 
	 * @return vertical padding of label backdrop.
	 */
	public int getBackdropPaddingY() {
		return getValue(Property.BACKDROP_PADDING_Y, getDefaultValues().getBackdropPaddingY());
	}

	/**
	 * If <code>true</code>, draw a background behind the tick labels.
	 * 
	 * @param showLabelBackdrop if <code>true</code>, draw a background behind the tick labels.
	 */
	public void setShowLabelBackdrop(boolean showLabelBackdrop) {
		setValue(Property.SHOW_LABEL_BACKDROP, showLabelBackdrop);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, draw a background behind the tick labels.
	 * 
	 * @return if <code>true</code>, draw a background behind the tick labels.
	 */
	public boolean isShowLabelBackdrop() {
		return getValue(Property.SHOW_LABEL_BACKDROP, getDefaultValues().isShowLabelBackdrop());
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels An array of labels to display.
	 */
	public void setLabels(String... labels) {
		setArrayValue(Property.LABELS, ArrayString.fromOrNull(labels));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets an array of labels to display.
	 * 
	 * @param labels A list of labels to display.
	 */
	public void setLabels(List<String> labels) {
		setArrayValue(Property.LABELS, ArrayString.fromOrNull(labels));
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the array of labels to display.
	 * 
	 * @return the array of labels to display.
	 */
	public List<String> getLabels() {
		ArrayString array = getArrayValue(Property.LABELS);
		return ArrayListHelper.list(array);
	}

	/**
	 * Sets the property controls the ticks generation.
	 * 
	 * @param source property controls the ticks generation.
	 */
	public void setSource(TickSource source) {
		setValue(Property.SOURCE, source);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the property controls the ticks generation.
	 * 
	 * @return property controls the ticks generation.
	 */
	public TickSource getSource() {
		return getValue(Property.SOURCE, TickSource.class, getDefaultValues().getSource());
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @param precision if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	public void setPrecision(int precision) {
		setValue(Property.PRECISION, precision);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 * 
	 * @return if defined and stepSize is not specified, the step size will be rounded to this many decimal places.
	 */
	public int getPrecision() {
		return getValue(Property.PRECISION, getDefaultValues().getPrecision());
	}

}