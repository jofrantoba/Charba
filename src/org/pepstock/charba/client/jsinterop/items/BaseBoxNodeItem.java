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
package org.pepstock.charba.client.jsinterop.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

/**
 * Base object which maps the CHART.JS chart items and represents main nodes of chart java script object.<br>
 * This is a wrapper of the CHART.JS item with all needed info.<br>
 * Implements all <code>get</code> methods to change java script object properties.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public abstract class BaseBoxNodeItem extends BaseBoxItem {
	
	private final MarginsItem margins;
	
	private final SizeItem minSize;

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		fullWidth,
		position,
		weight,
		width,
		height,
		maxWidth,
		maxHeight,
		margins,
		paddingTop,
		paddingRight,
		paddingLeft,
		paddingBottom,
		minSize
	}
	
	/**
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	BaseBoxNodeItem(NativeObject nativeObject) {
		super(nativeObject);
		// initializes the sub objects
		margins = new MarginsItem(getValue(Property.margins));
		minSize = new SizeItem(getValue(Property.minSize));
	}

	/**
	 * Returns the margin item.
	 * 
	 * @return the margin item.
	 */
	public final MarginsItem getMargins() {
		return margins;
	}

	/**
	 * Returns the min size item.
	 * 
	 * @return the min size item.
	 */
	public final SizeItem getMinSize() {
		return minSize;
	}

	/**
	 * Returns the full width in pixel.
	 * 
	 * @return the full width in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#BOOLEAN}.
	 */
	public final boolean isFullWidth() {
		return getValue(Property.fullWidth, UndefinedValues.BOOLEAN);
	}

	/**
	 * Returns the position of node.
	 * 
	 * @return the position of node. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 */
	public final Position getPosition() {
		return getValue(Property.position, Position.class, Position.top);
	}

	/**
	 * Returns the weight.
	 * 
	 * @return the weight. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public final double getWeight() {
		return getValue(Property.weight, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the width in pixel.
	 * 
	 * @return the width in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getWidth() {
		return getValue(Property.width, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the height in pixel.
	 * 
	 * @return the height in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getHeight() {
		return getValue(Property.height, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the max width in pixel.
	 * 
	 * @return the max width in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public final double getMaxWidth() {
		return getValue(Property.maxWidth, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the max height in pixel.
	 * 
	 * @return the max height in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#DOUBLE}.
	 */
	public final double getMaxHeight() {
		return getValue(Property.maxHeight, UndefinedValues.DOUBLE);
	}

	/**
	 * Returns the padding top in pixel.
	 * 
	 * @return the padding top in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getPaddingTop() {
		return getValue(Property.paddingTop, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding right in pixel.
	 * 
	 * @return the padding right in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getPaddingRight() {
		return getValue(Property.paddingRight, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding bottom in pixel.
	 * 
	 * @return the padding bottom in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getPaddingBottom() {
		return getValue(Property.paddingBottom, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the padding left in pixel.
	 * 
	 * @return the padding left in pixel. Default is {@link org.pepstock.charba.client.jsinterop.items.UndefinedValues#INTEGER}.
	 */
	public final int getPaddingLeft() {
		return getValue(Property.paddingLeft, UndefinedValues.INTEGER);
	}

}