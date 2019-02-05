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
package org.pepstock.charba.client.ext.labels;

import java.util.List;

import org.pepstock.charba.client.Defaults;
import org.pepstock.charba.client.colors.ColorBuilder;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.ArrayImage;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainer;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.ext.labels.LabelsOptionsFactory.LabelsDefaultsOptionsFactory;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * This is the object to map the LABELS plugin options, both at chart and global level.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class LabelsOptions extends NativeObjectContainer {
	// defaults global options instance
	private LabelsDefaultsOptions defaultsOptions;
	// defaults global options factory
	private final LabelsDefaultsOptionsFactory defaultsFactory = new LabelsDefaultsOptionsFactory();

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		render,
		precision,
		showZero,
		fontSize,
		fontColor,
		fontStyle,
		fontFamily,
		textShadow,
		shadowBlur,
		shadowOffsetX,
		shadowOffsetY,
		shadowColor,
		arc,
		position,
		overlap,
		showActualPercentages,
		images,
		outsidePadding,
		textMargin
	}

	/**
	 * Creates an empty object with plugin options.
	 */
	public LabelsOptions() {
		// creates an empty object
		super(null);
		// checks if the default global options has been added for the plugin
		if (Defaults.get().getGlobal().getPlugins().hasOptions(LabelsPlugin.ID)) {
			// reads the default default global options
			defaultsOptions = Defaults.get().getGlobal().getPlugins().getOptions(LabelsPlugin.ID, defaultsFactory);
		} else {
			// if here, no default global option
			// then the plugin will use the static defaults
			defaultsOptions = new LabelsDefaultsOptions();
		}
	}

	/**
	 * Creates the object using an existing options instance.<br>
	 * This is used ONLY to read options instance into global or chart.
	 * 
	 * @param nativeObject the object using an existing options instance
	 */
	LabelsOptions(NativeObject nativeObject, LabelsDefaultsOptions defaultsOptions) {
		super(nativeObject);
		// stores default options
		this.defaultsOptions = defaultsOptions;
	}

	/**
	 * Sets what data must be showed.
	 * 
	 * @param render what data must be showed.
	 */
	public final void setRender(Render render) {
		// checks is trying to set as callback (not allowed)
		if (!Render.callback.equals(render)) {
			setValue(Property.render, render);
		} else {
			throw new IllegalArgumentException("Render " + Render.callback + " can not be used to set the render");
		}
	}

	/**
	 * Returns what data must be showed.
	 * 
	 * @return what data must be showed. Default is {@link Render#value}. If the render is defined by a callback, returns
	 *         {@link Render#callback}.
	 */
	public final Render getRender() {
		// gets the render type
		ObjectType type = type(Property.render);
		// checks if is not a function (no callback)
		if (!ObjectType.Function.equals(type)) {
			return getValue(Property.render, Render.class, Render.value);
		}
		// returns callback
		return Render.callback;
	}

	/**
	 * Sets the precision for percentage.
	 * 
	 * @param precision the precision for percentage
	 */
	public final void setPrecision(int precision) {
		setValue(Property.precision, precision);
	}

	/**
	 * Returns the precision for percentage.
	 * 
	 * @return the precision for percentage. Default is 0.
	 */
	public final int getPrecision() {
		return getValue(Property.precision, defaultsOptions.getPrecision());
	}

	/**
	 * Sets whether or not labels of value 0 are displayed.
	 * 
	 * @param showZero whether or not labels of value 0 are displayed.
	 */
	public final void setShowZero(boolean showZero) {
		setValue(Property.showZero, showZero);
	}

	/**
	 * Returns whether or not labels of value 0 are displayed.
	 * 
	 * @return whether or not labels of value 0 are displayed. Default is false.
	 */
	public final boolean isShowZero() {
		return getValue(Property.showZero, defaultsOptions.isShowZero());
	}

	/**
	 * Sets the font size.
	 * 
	 * @param size the font size.
	 */
	public final void setFontSize(int size) {
		setValue(Property.fontSize, size);
	}

	/**
	 * Returns the the font size.
	 * 
	 * @return the font size. Default is <code>Defaults.get().getGlobal().getDefaultFontSize()</code>.
	 */
	public final int getFontSize() {
		return getValue(Property.fontSize, defaultsOptions.getFontSize());
	}

	/**
	 * Sets the font color as color.
	 * 
	 * @param color the font color as color.
	 */
	public final void setFontColor(IsColor color) {
		setFontColor(color.toRGBA());
	}

	/**
	 * Sets the font color as string.
	 * 
	 * @param color the font color as string.
	 */
	public final void setFontColor(String color) {
		setValue(Property.fontColor, color);
	}

	/**
	 * Returns the the font color as string.
	 * 
	 * @return the font color. Default is <code>Defaults.get().getGlobal().getDefaultFontColorAsString()</code>, even if the
	 *         font color callback has been set.
	 */
	public final String getFontColorAsString() {
		// gets the font color type
		ObjectType type = type(Property.fontColor);
		// checks if is not a function (no callback)
		if (!ObjectType.Function.equals(type)) {
			return getValue(Property.fontColor, defaultsOptions.getFontColorAsString());
		}
		// returns default
		return defaultsOptions.getFontColorAsString();
	}

	/**
	 * Returns the the font color as color.
	 * 
	 * @return the font color.
	 */
	public final IsColor getFontColor() {
		return ColorBuilder.parse(getFontColorAsString());
	}

	/**
	 * Sets the font style.
	 * 
	 * @param fontStyle the font style.
	 */
	public final void setFontStyle(FontStyle fontStyle) {
		setValue(Property.fontStyle, fontStyle);
	}

	/**
	 * Returns the font style.
	 * 
	 * @return the font style. Default is <code>Defaults.get().getGlobal().getDefaultFontStyle()</code>.
	 */
	public final FontStyle getFontStyle() {
		return getValue(Property.fontStyle, FontStyle.class, defaultsOptions.getFontStyle());
	}

	/**
	 * Sets the font family
	 * 
	 * @param fontFamily the font family
	 */
	public final void setFontFamily(String fontFamily) {
		setValue(Property.fontFamily, fontFamily);
	}

	/**
	 * Returns the font family.
	 * 
	 * @return the font family. Default is <code>Defaults.get().getGlobal().getDefaultFontFamily()</code>.
	 */
	public final String getFontFamily() {
		return getValue(Property.fontFamily, defaultsOptions.getFontFamily());
	}

	/**
	 * Sets if draws text shadows under labels.
	 * 
	 * @param textShadow <code>true</code> if draws text shadows under labels.
	 */
	public final void setTextShadow(boolean textShadow) {
		setValue(Property.textShadow, textShadow);
	}

	/**
	 * Returns if draws text shadows under labels.
	 * 
	 * @return <code>true</code> if draws text shadows under labels. Default is <code>false</code>.
	 */
	public final boolean isTextShadow() {
		return getValue(Property.textShadow, defaultsOptions.isTextShadow());
	}

	/**
	 * Sets the text shadow intensity.
	 * 
	 * @param shadowBlur the text shadow intensity.
	 */
	public final void setShadowBlur(int shadowBlur) {
		setValue(Property.shadowBlur, shadowBlur);
	}

	/**
	 * Returns the text shadow intensity.
	 * 
	 * @return the text shadow intensity. Default is 6.
	 */
	public final int getShadowBlur() {
		return getValue(Property.shadowBlur, defaultsOptions.getShadowBlur());
	}

	/**
	 * Sets the text shadow X offset.
	 * 
	 * @param shadowOffsetX the text shadow X offset.
	 */
	public final void setShadowOffsetX(int shadowOffsetX) {
		setValue(Property.shadowOffsetX, shadowOffsetX);
	}

	/**
	 * Returns the text shadow X offset.
	 * 
	 * @return the text shadow X offset. Default is 3.
	 */
	public final int getShadowOffsetX() {
		return getValue(Property.shadowOffsetX, defaultsOptions.getShadowOffsetX());
	}

	/**
	 * Sets the text shadow Y offset.
	 * 
	 * @param shadowOffsetY the text shadow Y offset.
	 */
	public final void setShadowOffsetY(int shadowOffsetY) {
		setValue(Property.shadowOffsetY, shadowOffsetY);
	}

	/**
	 * Returns the text shadow Y offset.
	 * 
	 * @return the text shadow Y offset. Default is 3.
	 */
	public final int getShadowOffsetY() {
		return getValue(Property.shadowOffsetY, defaultsOptions.getShadowOffsetY());
	}

	/**
	 * Sets the text shadow color as color.
	 * 
	 * @param shadowColor the text shadow color as color.
	 */
	public final void setShadowColor(IsColor shadowColor) {
		setShadowColor(shadowColor.toRGBA());
	}

	/**
	 * Sets the text shadow color as string.
	 * 
	 * @param shadowColor the text shadow color as string.
	 */
	public final void setShadowColor(String shadowColor) {
		setValue(Property.shadowColor, shadowColor);
	}

	/**
	 * Returns the text shadow color as string.
	 * 
	 * @return the text shadow color as string. Default is <code>rgba(0,0,0,0.3)</code>.
	 */
	public final String getShadowColorAsString() {
		return getValue(Property.shadowColor, defaultsOptions.getShadowColorAsString());
	}

	/**
	 * Returns the text shadow color as color.
	 * 
	 * @return the text shadow color as color.
	 */
	public final IsColor getShadowColor() {
		return ColorBuilder.parse(getShadowColorAsString());
	}

	/**
	 * Sets if draws label in arc. For bar chart this is ignored.
	 * 
	 * @param arc if draws label in arc.
	 */
	public final void setArc(boolean arc) {
		setValue(Property.arc, arc);
	}

	/**
	 * Returns if draws label in arc.
	 * 
	 * @return <code>true</code> if draws label in arc. Default is <code>false</code>.
	 */
	public final boolean isArc() {
		return getValue(Property.arc, defaultsOptions.isArc());
	}

	/**
	 * + Sets the position to draw label. For bar chart this is ignored.
	 * 
	 * @param position the position to draw label.
	 */
	public final void setPosition(Position position) {
		setValue(Property.position, position.getValue());
	}

	/**
	 * Returns the position to draw label.
	 * 
	 * @return the position to draw label. Default is {@link Position#defaults}.
	 */
	public final Position getPosition() {
		String value = getValue(Property.position, defaultsOptions.getPositionAsString());
		return Position.getPositionByValue(value);
	}

	/**
	 * Sets if draws label even it's overlap. For bar chart this is ignored.
	 * 
	 * @param overlap if draws label even it's overlap.
	 */
	public final void setOverlap(boolean overlap) {
		setValue(Property.overlap, overlap);
	}

	/**
	 * Returns if draws label even it's overlap.
	 * 
	 * @return <code>true</code>if draws label even it's overlap. Default is <code>true</code>.
	 */
	public final boolean isOverlap() {
		return getValue(Property.overlap, defaultsOptions.isOverlap());
	}

	/**
	 * Sets if shows the real calculated percentages from the values and don't apply the additional logic to fit the percentages
	 * to 100 in total.
	 * 
	 * @param showActualPercentages if shows the real calculated percentages from the values and don't apply the additional
	 *            logic to fit the percentages to 100 in total.
	 */
	public final void setShowActualPercentages(boolean showActualPercentages) {
		setValue(Property.showActualPercentages, showActualPercentages);
	}

	/**
	 * Returns if shows the real calculated percentages from the values and don't apply the additional logic to fit the
	 * percentages to 100 in total.
	 * 
	 * @return <code>true</code>if shows the real calculated percentages from the values and don't apply the additional logic to
	 *         fit the percentages to 100 in total. Default is <code>false</code>.
	 */
	public final boolean isShowActualPercentages() {
		return getValue(Property.showActualPercentages, defaultsOptions.isShowActualPercentages());
	}

	/**
	 * Sets the padding when position is {@link Position#outside}.
	 * 
	 * @param outsidePadding the padding when position is {@link Position#outside}.
	 */
	public final void setOutsidePadding(int outsidePadding) {
		setValue(Property.outsidePadding, outsidePadding);
	}

	/**
	 * Returns the padding when position is {@link Position#outside}.
	 * 
	 * @return the padding when position is {@link Position#outside}. Default is 2.
	 */
	public final int getOutsidePadding() {
		return getValue(Property.outsidePadding, defaultsOptions.getOutsidePadding());
	}

	/**
	 * Sets the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @param textMargin the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 */
	public final void setTextMargin(int textMargin) {
		setValue(Property.textMargin, textMargin);
	}

	/**
	 * Returns the margin of text when position is {@link Position#outside} or {@link Position#border}.
	 * 
	 * @return the margin of text when position is {@link Position#outside} or {@link Position#border}. Default is 2.
	 */
	public final int getTextMargin() {
		return getValue(Property.textMargin, defaultsOptions.getTextMargin());
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public final void setImages(ImageResource... images) {
		// checks if argument is consistent
		if (images != null) {
			// creates a temporary array
			ImageElement[] array = new ImageElement[images.length];
			// scans passed array of images
			for (int i = 0; i < images.length; i++) {
				// transform a image resource into image element by image object
				// creates image object
				Image img = new Image(images[i]);
				// stores into array changing in image element
				array[i] = ImageElement.as(img.getElement());
			}
			// stores it
			setImages(array);
		} else {
			// if here, argument is null
			// then removes property
			remove(Property.images);
		}
	}

	/**
	 * Sets the images when {@link Render} is {@link Render#image}.
	 * 
	 * @param images images when {@link Render} is {@link Render#image}.
	 */
	public final void setImages(ImageElement... images) {
		setArrayValue(Property.images, ArrayImage.of(images));
	}

	/**
	 * Returns the images when {@link Render} is {@link Render#image}.
	 * 
	 * @return the images when {@link Render} is {@link Render#image} or an empty list.
	 */
	public final List<ImageElement> getImages() {
		// gets array
		ArrayImage array = getArrayValue(Property.images);
		return ArrayListHelper.list(array);
	}
}
