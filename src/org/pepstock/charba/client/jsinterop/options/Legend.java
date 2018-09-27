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

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.commons.Checker;
import org.pepstock.charba.client.jsinterop.commons.Enumer;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Legend extends BaseModel<BaseOptions<?,?>, IsDefaultLegend, NativeLegend> {
	
	private LegendLabels labels;
	
	Legend(BaseOptions<?, ?> options, IsDefaultLegend defaultValues, NativeLegend delegated) {
		super(options, defaultValues, delegated == null ? new NativeLegend() : delegated);
		labels = new LegendLabels(this, getDefaultValues().getLabels(), getDelegated().getLabels());
	}

	/**
	 * @return the labels
	 */
	public LegendLabels getLabels() {
		return labels;
	}

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	public void setDisplay(boolean display) {
		getDelegated().setDisplay(display);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown. Default is true.
	 */
	public boolean isDisplay() {
		return Checker.check(getDelegated().isDisplay(), getDefaultValues().isDisplay());
	}

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	public void setFullWidth(boolean fullWidth) {
		getDelegated().setFullWidth(fullWidth);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	public boolean isFullWidth() {
		return Checker.check(getDelegated().isFullWidth(), getDefaultValues().isFullWidth());
	}

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	public void setReverse(boolean reverse) {
		getDelegated().setReverse(reverse);
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order. Default is false.
	 */
	public boolean isReverse() {
		return Checker.check(getDelegated().isReverse(), getDefaultValues().isReverse());
	}

	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public void setPosition(Position position) {
		getDelegated().setPosition(position.name());
		// checks if the node is already added to parent
		checkAndAddToParent();
	}

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	public Position getPosition() {
		return Enumer.deserialize(getDelegated().getPosition(), getDefaultValues().getPosition(), Position.class, Position.top);
	}
	
	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.BaseModel#addToParent()
	 */
	@Override
	protected void addToParent() {
		if (getParent().getDelegated().getLegend() == null) {
			getParent().getDelegated().setLegend(getDelegated());
		}
	}

}