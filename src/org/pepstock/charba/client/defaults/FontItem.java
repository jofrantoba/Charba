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
package org.pepstock.charba.client.defaults;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.enums.FontStyle;

/**
 * Base object to map font options of globals.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class FontItem extends AbstractItem {

	/**
	 * Name of fields of JavaScript object.
	 */
	private enum Property implements Key
	{
		fontSize,
		fontStyle,
		fontColor,
		fontFamily,
	}

	/**
	 * Builds the object with parent item and child.
	 * 
	 * @param parent parent item
	 * @param childKey key of child
	 */
	protected FontItem(AbstractItem parent, Key childKey) {
		super(parent, childKey);
	}

	/**
	 * Sets the font size.
	 * 
	 * @param fontSize the font size.
	 */
	public void setFontSize(int fontSize) {
		setValue(Property.fontSize, fontSize);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the font size.
	 * 
	 * @return the font size. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontSize()}.
	 */
	public int getFontSize() {
		return getValue(Property.fontSize, Defaults.getGlobal().getDefaultFontSize());
	}

	/**
	 * Sets the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @param fontStyle Font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit).
	 * 
	 * @return the font style, follows CSS font-style options (i.e. normal, italic, oblique, initial, inherit). Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontStyle()}.
	 * @see org.pepstock.charba.client.enums.FontStyle
	 */
	public FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, FontStyle.normal);
	}

	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	public void setFontColor(IsColor fontColor) {
		setFontColor(fontColor.toRGBA());
	}
	
	/**
	 * Sets the font color
	 * 
	 * @param fontColor Font color
	 */
	public void setFontColor(String fontColor) {
		setValue(Property.fontColor, fontColor);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontColor()}.
	 */
	public String getFontColorAsString() {
		return getValue(Property.fontColor, Defaults.getGlobal().getDefaultFontColorAsString());
	}

	/**
	 * Returns the font color
	 * 
	 * @return Font color. Default is {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontColor()}.
	 */
	public IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}
	
	/**
	 * Sets the font family, follows CSS font-family options.
	 * 
	 * @param fontFamily Font family, follows CSS font-family options.
	 */
	public void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns the font family, follows CSS font-family options.
	 * 
	 * @return Font family, follows CSS font-family options. Default is
	 *         {@link org.pepstock.charba.client.defaults.global.Options#getDefaultFontFamily()}.
	 */
	public String getFontFamily() {
		return getValue(Property.fontFamily, Defaults.getGlobal().getDefaultFontFamily());
	}

}