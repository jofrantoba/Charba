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

import java.util.List;

import org.pepstock.charba.client.jsinterop.commons.ArrayListHelper;
import org.pepstock.charba.client.jsinterop.commons.ArrayString;
import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.ObjectType;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;
import org.pepstock.charba.client.jsinterop.enums.Position;
import org.pepstock.charba.client.jsinterop.items.UndefinedValues;

/**
 * Configures the default chart title which defines text to draw at the top of the chart.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Title extends FontItem<Options, IsDefaultTitle> implements IsDefaultTitle {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		display,
		fontStyle,
		position,
		padding,
		fullWidth,
		lineHeight,
		text
	}

	/**
	 * Creates the object with the parent, the key of this element, default values and native object to map java script
	 * properties.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param defaultValues default provider
	 * @param nativeObject native object to map java script properties
	 */
	Title(Options options, Key childKey, IsDefaultTitle defaultValues, NativeObject nativeObject) {
		super(options, childKey, defaultValues, nativeObject);
	}

	/**
	 * Sets <code>true</code> if the title is shown.
	 * 
	 * @param display if <code>true</code> the title is shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if the title is shown.
	 * 
	 * @return if <code>true</code> the title is shown.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, getDefaultValues().isDisplay());
	}

	/**
	 * Sets the position of title.
	 * 
	 * @param position the position of title.
	 */
	public void setPosition(Position position) {
		setValue(Property.position, position);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the position of title.
	 * 
	 * @return the position of title.
	 */
	public Position getPosition() {
		return getValue(Property.position, Position.class, getDefaultValues().getPosition());
	}

	/**
	 * Sets the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @param padding padding to apply around labels. Only top and bottom are implemented.
	 */
	public void setPadding(int padding) {
		setValue(Property.padding, padding);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the padding to apply around labels. Only top and bottom are implemented.
	 * 
	 * @return padding to apply around labels. Only top and bottom are implemented.
	 */
	public int getPadding() {
		return getValue(Property.padding, getDefaultValues().getPadding());
	}

	/**
	 * If <code>true</code>, marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth if <code>true</code>, marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		setValue(Property.fullWidth, fullWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return <code>true</code> if marks that this box should take the full width of the canvas (pushing down other boxes).
	 */
	public boolean isFullWidth() {
		return getValue(Property.fullWidth, getDefaultValues().isFullWidth());
	}

	/**
	 * Sets the height of an individual line of text.
	 * 
	 * @param lineHeight height of an individual line of text.
	 */
	public void setLineHeight(double lineHeight) {
		setValue(Property.lineHeight, lineHeight);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the height of an individual line of text.
	 * 
	 * @return the height of an individual line of text.
	 */
	public double getLineHeight() {
		return getValue(Property.lineHeight, getDefaultValues().getLineHeight());
	}

	/**
	 * Sets the title text to display. If specified as an array, text is rendered on multiple lines.
	 * 
	 * @param text the title text to display. If specified as an array, text is rendered on multiple lines.
	 */
	public void setText(String... text) {
		// check if text is consistent
		if (text != null) {
			// checks if there is more than 1 element
			if (text.length > 1) {
				// stores the array
				setArrayValue(Property.text, ArrayString.of(text));
			} else {
				// in this case there is only 1 element and then
				// stores as string
				setValue(Property.text, text[0]);
			}
			// checks if the node is already added to parent
			checkAndAddToParent();
		} else {
			// being null
			// remove the key if exists
			removeIfExists(Property.text);
		}
	}

	/**
	 * Returns the title text to display, as a list of strings.
	 * 
	 * @return a list of strings or <code>null</code> if not exist
	 */
	public List<String> getText() {
		// gets the type of the property
		ObjectType type = type(Property.text);
		// if it's an array
		if (ObjectType.Array.equals(type)) {
			// reads as array
			// and returns it
			ArrayString array = getArrayValue(Property.text);
			return ArrayListHelper.list(array);
		} else if (has(Property.text)) {
			// if there is the property
			// and we are here, loads it as string
			return ArrayListHelper.list(ArrayString.of(getValue(Property.text, UndefinedValues.STRING)));
		}
		// if not exists, null.
		return null;
	}
}