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
package org.pepstock.charba.client.callbacks.impl;

import java.util.List;

import org.pepstock.charba.client.AbstractChart;
import org.pepstock.charba.client.callbacks.TickCallback;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.items.DatasetMetaItem;
import org.pepstock.charba.client.items.TickItem;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * Implementation of tick callback in order to avoid that when all datasets are hidden, the ticks will get
 * a wrong double precision.
 * 
 * @author Andrea "Stock" Stocchero
 * @see org.pepstock.charba.client.callbacks.TickCallback
 */
public class NoSelectedDatasetTicksCallback implements TickCallback {

	// oit formats the ticks with 1 digits of precision
	private final static NumberFormat FORMAT = NumberFormat.getFormat("0.0");
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.callbacks.TickCallback#onCallback(org.pepstock.charba.client.AbstractChart, org.pepstock.charba.client.items.TickItem)
	 */
	@Override
	public String onCallback(AbstractChart<?, ?> chart, TickItem item) {
		// flags to know if all datasets are hidden
		boolean allHidden = false;
		// gets datasets
		List<Dataset> dss = chart.getData().getDatasets();
		// scans them by for cycle to have the index for retrieving 
		// the dataset metadata
		for (int i=0; i<dss.size(); i++){
			// gets metadata to know if is hidden
			DatasetMetaItem metadata = chart.getDatasetMeta(i);
			// checks if metadata is null.
			// it happens when the chart is drawing for the first time
			// but at the first time 1 dataset is always visible
			if (metadata != null) {
				// OR on dataset visibility
				allHidden = allHidden || metadata.isHidden();
			}
		}
		// if all datasets are hidden
		if (allHidden) {
			// uses the tick value (double) provided by CHART.js
			// applying the number format
			return FORMAT.format(item.getValue());
		}
		// otherwise will return the tick value as string
		return String.valueOf(item.getValue());
	}

}