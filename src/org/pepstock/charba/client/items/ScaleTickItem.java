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
package org.pepstock.charba.client.items;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.NativeObjectContainerFactory;

/**
 * The tick item maps the objects passed a {@link ScaleItem}.<br>
 * This is a wrapper of the CHART.JS item with all needed info.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class ScaleTickItem extends AbstractTickItem {

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		INDEX("_index"),
		LABEL("label"),
		VALUE("value");

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
	 * Creates the item using a native java script object which contains all properties.
	 * 
	 * @param nativeObject native java script object which contains all properties.
	 */
	ScaleTickItem(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Returns the index of the tick.
	 * 
	 * @return the index of the tick or {@link UndefinedValues#INTEGER} if missing.
	 */
	public final int getIndex() {
		return getValue(Property.INDEX, UndefinedValues.INTEGER);
	}

	/**
	 * Returns the label of the tick.
	 * 
	 * @return the label of the tick or {@link UndefinedValues#STRING} if missing.
	 */
	public final String getLabel() {
		return getValue(Property.LABEL, UndefinedValues.STRING);
	}

	/**
	 * Returns the value of the tick as string.
	 * 
	 * @return the value of the tick or {@link UndefinedValues#STRING} if missing or not a string.
	 */
	public final String getValueAsString() {
		return getValueForMultipleKeyTypes(Property.VALUE, UndefinedValues.STRING);
	}

	/**
	 * Returns the value of the tick as double.
	 * 
	 * @return the value of the tick or {@link UndefinedValues#DOUBLE} if missing or not a double.
	 */
	public final double getValueAsDouble() {
		return getValueForMultipleKeyTypes(Property.VALUE, UndefinedValues.DOUBLE);
	}

	/**
	 * Inner class to create time tick item by a native object.
	 * 
	 * @author Andrea "Stock" Stocchero
	 */
	static final class ScaleTickItemFactory implements NativeObjectContainerFactory<ScaleTickItem> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.NativeObjectContainerFactory#create(org.pepstock.charba.client.commons.
		 * NativeObject)
		 */
		@Override
		public ScaleTickItem create(NativeObject nativeObject) {
			return new ScaleTickItem(nativeObject);
		}
	}

}