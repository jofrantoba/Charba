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
package org.pepstock.charba.client.jsinterop.events;

/**
 * Event handler for clicking on the chart legend.
 * 
 * @author Andrea "Stock" Stocchero
 * @since 2.0
 */
public interface LegendClickEventHandler extends ChartEventHandler {

	/**
	 * Invoked when the user clicks on the chart legend.
	 * 
	 * @param event legend click event
	 */
	void onClick(LegendClickEvent event);

}