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

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.defaults.IsDefaultScaleLabel;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class ScaleLabel extends FontItem<Scale, IsDefaultScaleLabel> implements IsDefaultScaleLabel {

	private final ScaleLabelPadding padding;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		PADDING("padding"),
		DISPLAY("display"),
		LABEL_STRING("labelString"),
		LINE_HEIGHT("lineHeight");

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
	 * @param scale scale/axis of this object.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	ScaleLabel(Scale scale, Key childKey, IsDefaultScaleLabel defaultValues, NativeObject delegated) {
		super(scale, childKey, defaultValues, delegated);
		// gets sub element
		padding = new ScaleLabelPadding(this, Property.PADDING, getDefaultValues().getPadding(), getValue(Property.PADDING));
	}

	/**
	 * Returns the padding element.
	 * 
	 * @return the padding
	 */
	public ScaleLabelPadding getPadding() {
		return padding;
	}

	/**
	 * If <code>true</code>, display the axis title.
	 * 
	 * @param display if <code>true</code>, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.DISPLAY, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If <code>true</code>, display the axis title.
	 * 
	 * @return if <code>true</code>, display the axis title.
	 */
	public boolean isDisplay() {
		return getValue(Property.DISPLAY, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the text for the scale string.
	 * 
	 * @param labelString the text for the scale string.
	 */
	public void setLabelString(String labelString) {
		setValue(Property.LABEL_STRING, labelString);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return the text for the scale string.
	 */
	public String getLabelString() {
		return getValue(Property.LABEL_STRING, getDefaultValues().getLabelString());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(String lineHeight) {
		setValue(Property.LINE_HEIGHT, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		// creates default
		double defaultValue = getDefaultValues().getLineHeight();
		// checks type if number
		if (ObjectType.NUMBER.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as double
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public String getLineHeightAsString() {
		// creates default
		String defaultValue = String.valueOf(getDefaultValues().getLineHeight());
		// checks type if string
		if (ObjectType.STRING.equals(type(Property.LINE_HEIGHT))) {
			// reads and returns as string
			return getValue(Property.LINE_HEIGHT, defaultValue);
		}
		// if here, is not a number
		// then returns the default
		return defaultValue;
	}

}