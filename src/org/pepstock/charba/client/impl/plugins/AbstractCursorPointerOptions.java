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
package org.pepstock.charba.client.impl.plugins;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.plugins.AbstractPluginOptions;

import com.google.gwt.dom.client.Style.Cursor;

/**
 * Common configuration options for plugins which are managing cursor style.
 * 
 * @author Andrea "Stock" Stocchero
 */
abstract class AbstractCursorPointerOptions extends AbstractPluginOptions {

	/**
	 * Name of properties of native object.
	 */
	enum Property implements Key
	{
		CURSOR_POINTER("cursorPointer"),
		CURSOR_DEFAULT("cursorDefault");

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
	 * Creates new plugin options with plugin ID, using a native object instance.
	 * 
	 * @param pluginId plugin ID
	 * @param nativeObject native object which represents the plugin options as native object
	 */
	AbstractCursorPointerOptions(String pluginId, NativeObject nativeObject) {
		super(pluginId, nativeObject);
	}

	/**
	 * Creates new plugin options with plugin ID, creating new native options.
	 * 
	 * @param pluginId plugin ID
	 */
	AbstractCursorPointerOptions(String pluginId) {
		super(pluginId);
	}

	/**
	 * Returns the default cursor pointer as string to use when a property is missing.
	 * 
	 * @return the default cursor pointer as string to use when a property is missing
	 */
	abstract String getCursorPointerAsString();

	/**
	 * Sets the cursor type when the cursor is over the dataset item.
	 * 
	 * @param cursor cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	public final void setCursorPointer(Cursor cursor) {
		// checks if cursor is consistent
		if (cursor != null) {
			setValue(Property.CURSOR_POINTER, cursor.name());
		}
	}

	/**
	 * Returns the cursor type when the cursor is over the dataset item.
	 * 
	 * @return cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	public final Cursor getCursorPointer() {
		String name = getValue(Property.CURSOR_POINTER, getCursorPointerAsString());
		return Cursor.valueOf(name);
	}

	/**
	 * Sets the cursor type before changing it. Needed to set it back.
	 * 
	 * @param cursor cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	final void setCurrentCursor(Cursor cursor) {
		// checks if cursor argument is consistent
		if (cursor != null) {
			setValue(Property.CURSOR_DEFAULT, cursor.name());
		}
	}

	/**
	 * Returns the cursor type before changing it. Needed to set it back.
	 * 
	 * @return cursor type
	 * @see com.google.gwt.dom.client.Style.Cursor
	 */
	final Cursor getCurrentCursor() {
		String name = getValue(Property.CURSOR_DEFAULT, Cursor.DEFAULT.name());
		return Cursor.valueOf(name);
	}

}
