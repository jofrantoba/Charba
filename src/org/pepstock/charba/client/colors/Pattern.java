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
package org.pepstock.charba.client.colors;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Entity to apply a canvas pattern into a dataset as background.<br>
 * See <a href="https://www.toptal.com/designers/subtlepatterns/">here</a> some interesting pattern images.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class Pattern extends CanvasObject {
	// exception message
	private static final String IMG_NULL_MESSAGE = "Image instance is null!";

	// default instance of image. The image SHOULD NOT NEVER BE NULL!
	private final static ImageElement DEFAULT_IMAGE = null;

	/**
	 * Name of properties of native object.
	 * ALL INTERNAL USE ONLY
	 */
	private enum Property implements Key
	{
		_charbaPatternImg,
		_charbaPatternRepetition
	}

	/**
	 * Creates the object using an image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param image image to use as pattern
	 */
	public Pattern(ImageResource image) {
		this(image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using an image to use in the pattern.<br>
	 * The repetition used is repeat.
	 * 
	 * @param image image to use as pattern
	 */
	public Pattern(ImageElement image) {
		this(image, Context2d.Repetition.REPEAT);
	}

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(ImageResource image, Context2d.Repetition repetition) {
		// checks if image is not consistent
		if (image != null) {
			// reads image
			Image img = new Image(image);
			// creates pattern
			setValue(Property._charbaPatternImg, ImageElement.as(img.getElement()));
			setValue(Property._charbaPatternRepetition, repetition == null ? Context2d.Repetition.REPEAT.name() : repetition.name());
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException(IMG_NULL_MESSAGE);
		}
	}

	/**
	 * Creates the object using an image to use in the pattern and repetition to apply to pattern.
	 * 
	 * @param image image to use as pattern
	 * @param repetition repetition value to apply to pattern
	 */
	public Pattern(ImageElement image, Context2d.Repetition repetition) {
		// checks if image is not consistent
		if (image != null) {
			// creates pattern
			setValue(Property._charbaPatternImg, image);
			setValue(Property._charbaPatternRepetition, repetition == null ? Context2d.Repetition.REPEAT.name() : repetition.name());
		} else {
			// if here, image is null
			// then exception
			throw new IllegalArgumentException(IMG_NULL_MESSAGE);
		}
	}

	/**
	 * Creates the object using a native object where pattern info are stored.
	 * 
	 * @param nativeObject native object where pattern info are stored
	 */
	Pattern(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the image to use into pattern
	 * 
	 * @return the image to use into pattern
	 */
	public ImageElement getImage() {
		return getValue(Property._charbaPatternImg, DEFAULT_IMAGE);
	}

	/**
	 * Returns the repetition to use into pattern.
	 * 
	 * @return the repetition to use into pattern
	 */
	public Context2d.Repetition getRepetition() {
		String value = getValue(Property._charbaPatternRepetition, Context2d.Repetition.REPEAT.name());
		return Context2d.Repetition.valueOf(value);
	}

	/**
	 * Inner class to create pattern by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static final class PatternFactory implements NativeObjectContainerFactory<Pattern> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.jsinterop
		 * .commons.NativeObject)
		 */
		@Override
		public Pattern create(NativeObject nativeObject) {
			return new Pattern(nativeObject);
		}
	}

}