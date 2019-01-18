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

import org.pepstock.charba.client.jsinterop.commons.Key;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainer;
import org.pepstock.charba.client.jsinterop.commons.NativeObjectContainerFactory;
import org.pepstock.charba.client.jsinterop.plugins.InvalidPluginIdException;
import org.pepstock.charba.client.jsinterop.plugins.PluginIdChecker;

/**
 * Definitions about plugins options. This is used to configure plugins (mainly the global ones).<br>
 * Every plugin could have own configuration structure.<br>
 * The java script object key is the plugin id.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 *
 */
public final class Plugins extends AbstractModel<Options, Void> {

	/**
	 * Creates the object with the parent, the key of this element and native object to map java script properties.<br>
	 * No default values for this element.
	 * 
	 * @param options options of the chart.
	 * @param childKey the property name of this element to use to add it to the parent.
	 * @param nativeObject native object to map java script properties
	 */
	Plugins(Options options, Key childKey, NativeObject nativeObject) {
		// no default values for this element
		super(options, childKey, null, nativeObject);
	}

	/**
	 * Sets if a global plugin must be enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @param enabled <code>false</code> disable a global plugin.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public void setEnabled(String pluginId, boolean enabled) throws InvalidPluginIdException {
		// if null, removes the configuration
		if (enabled) {
			// removes configuration if exists
			remove(PluginIdChecker.key(pluginId));
		} else {
			// stores configuration
			setValue(PluginIdChecker.key(pluginId), true);
		}
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if a global plugin is enabled or not.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>false</code> if a global plugin is not enabled otherwise <code>true</code>.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public boolean isEnabled(String pluginId) throws InvalidPluginIdException {
		return getValue(PluginIdChecker.key(pluginId), true);
	}

	/**
	 * Sets the plugin options. If passed options is null, the configuration of plugin will be removed.
	 * 
	 * @param pluginId plugin id.
	 * @param options java script object used to configure the plugin. Pass <code>null</code> to remove the configuration if
	 *            exist.
	 * @param <T> type of native object container to store
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public <T extends NativeObjectContainer> void setOptions(String pluginId, T options) throws InvalidPluginIdException {
		// if null, removes the configuration
		if (options == null) {
			// removes configuration if exists
			remove(PluginIdChecker.key(pluginId));
		} else {
			// stores configuration
			setValue(PluginIdChecker.key(pluginId), options);
		}
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Checks if there is any options for a specific plugin, by its id.
	 * 
	 * @param pluginId plugin id.
	 * @return <code>true</code> if there is an options, otherwise <code>false</code>.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public boolean hasOptions(String pluginId) throws InvalidPluginIdException {
		return has(PluginIdChecker.key(pluginId));
	}

	/**
	 * Returns the plugin options, if exist. It uses a factory instance to create a native object container.
	 * 
	 * @param pluginId plugin id.
	 * @param factory factory instance to create a native object container.
	 * @param <T> type of native object container to return
	 * @return java script object used to configure the plugin or <code>null</code> if not exist.
	 * @throws InvalidPluginIdException occurs if the plugin id is invalid.
	 */
	public <T extends NativeObjectContainer> T getOptions(String pluginId, NativeObjectContainerFactory<T> factory) throws InvalidPluginIdException {
		return factory.create(getValue(PluginIdChecker.key(pluginId)));
	}

}