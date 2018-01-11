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
package org.pepstock.charba.client.defaults.global;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;
import org.pepstock.charba.client.defaults.FontItem;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.enums.Position;

/**
 * Configures the chart title which defines text to draw at the top of the chart.<br>
 * "weight"property is not present.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Title extends FontItem {

	private static final int DEFAULT_PADDING = 10;
	
	private static final boolean DEFAULT_FULL_WIDTH = true;

	private static final double DEFAULT_LINE_HEIGHT = 1.2D;
	
	private static final boolean DEFAULT_DISPLAY = false;

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		display,
		fontStyle,
		position,
		padding,
		fullWidth,
		lineHeight
	}

	/**
	 * Builds the object storing the chart instance.
	 * 
	 * @param chart chart instance
	 */
	Title(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	@Override
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, FontStyle.bold);
	}

	/**
	 * Sets if the title is shown.
	 * 
	 * @param display if the title is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		checkAndAddToParent();
	}

	/**
	 * Returns if the title is shown.
	 * 
	 * @return if the title is shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
		checkAndAddToParent();
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}
	
	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding Padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return Padding to apply around labels. Only top and bottom are implemented. Default is 10.
	 */
	public int getPadding() {
		return getValue(Property.padding, DEFAULT_PADDING);
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
		checkAndAddToParent();
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	public boolean isFullWidth() {
		return getValue(Property.fullWidth, DEFAULT_FULL_WIDTH);
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight Height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text. Default is 1.2
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, DEFAULT_LINE_HEIGHT);
	}

}