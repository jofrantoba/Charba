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
package org.pepstock.charba.client.colors.tiles;

import org.pepstock.charba.client.enums.PointStyle;

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * RECTROUNDED drawer to design a {@link PointStyle#RECT_ROUNDED} into tile.<br>
 * It designs the point style into the following tile section (A):<br>
 * <br>
 * 
 * <pre>
 * +---------+---------+
 * |                   |
 * |                   |
 * |                   |
 * |         A         |
 * |                   |
 * |                   |
 * |                   |
 * +---------+---------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class PointStyleRectRounded extends AbstractPointStyleShapeDrawer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.pepstock.charba.client.colors.tiles.AbstractPointStyleShapeDrawer#drawPointStyle(com.google.gwt.canvas.dom.client.
	 * Context2d, int, double, double, double, double, double)
	 */
	@Override
	protected void drawPointStyle(Context2d context, int size, double x, double y, double radius, double rotation, double rad) {
		// NOTE: the rounded rect implementation changed to use `arc` instead of
		// `quadraticCurveTo` since it generates better results when rect is
		// almost a circle. 0.516 (instead of 0.5) produces results with visually
		// closer proportion to the previous impl and it is inscribed in the
		// circle with `radius`. For more details, see the following PRs:
		// https://github.com/chartjs/Chart.js/issues/5597
		// https://github.com/chartjs/Chart.js/issues/5858
		double cornerRadius = radius * 0.516;
		double changedSize = radius - cornerRadius;
		double xOffset = Math.cos(rad + QUARTER_PI) * changedSize;
		double yOffset = Math.sin(rad + QUARTER_PI) * changedSize;
		context.arc(x - xOffset, y - yOffset, cornerRadius, rad - PI, rad - HALF_PI);
		context.arc(x + yOffset, y - xOffset, cornerRadius, rad - HALF_PI, rad);
		context.arc(x + xOffset, y + yOffset, cornerRadius, rad, rad + HALF_PI);
		context.arc(x - yOffset, y + xOffset, cornerRadius, rad + HALF_PI, rad + PI);
	}

}
