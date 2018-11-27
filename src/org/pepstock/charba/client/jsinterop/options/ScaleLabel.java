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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel;

/**
 * When creating a chart, you want to tell the viewer what data they are viewing. To do this, you need to label the axis.<br>
 * The scale label configuration defines options for the scale title. Note that this only applies to cartesian axes.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class ScaleLabel extends FontItem<Scale, IsDefaultScaleLabel> implements IsDefaultScaleLabel{
	
	private final ScaleLabelPadding padding;
	
	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		// sub elements
		padding,
		// properties
		display,
		labelString,
		lineHeight
	}


	ScaleLabel(Scale scale, Key childKey, IsDefaultScaleLabel defaultValues, NativeObject delegated) {
		super(scale, childKey, defaultValues, delegated);
		padding = new ScaleLabelPadding(this, Property.padding, getDefaultValues().getPadding(), getValue(Property.padding));
	}
	
	/**
	 * @return the padding
	 */
	public ScaleLabelPadding getPadding() {
		return padding;
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @param display if true, display the axis title.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * If true, display the axis title.
	 * 
	 * @return f true, display the axis title. Default is false
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the text for the scale string.
	 * 
	 * @param labelString The text for the scale string.
	 */
	public void setLabelString(String labelString) {
		setValue(Property.labelString, labelString);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the text for the scale string.
	 * 
	 * @return The text for the scale string. Default is "".
	 */
	public String getLabelString() {
		return getValue(Property.labelString, getDefaultValues().getLabelString());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, getDefaultValues().getLineHeight());
	}
}