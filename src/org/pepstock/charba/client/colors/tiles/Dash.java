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

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * DASH drawer to design a dash into tile.<br>
 * It designs a dash into the following tile sections (A and B):<br>
 * <br>
 * 
 * <pre>
 * +---------+---------+
 * |         |         |
 * |    A    |         |
 * |         |         |
 * +---------+---------+
 * |         |         |
 * |         |    B    |
 * |         |         |
 * +---------+---------+
 * </pre>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class Dash extends ShapeDrawer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.colors.tiles.ShapeDrawer#drawTile(com.google.gwt.canvas.dom.client.Context2d,
	 * java.lang.String, java.lang.String, int)
	 */
	@Override
	protected void drawTile(Context2d context, String backgroundColor, String shapeColor, int size) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// apply the stroke properties
		applyStrokeProperties(context, shapeColor, size);
		// designs the shape into A section
		drawDash(context, size, 0D, 0D);
		// designs the shape into B section
		drawDash(context, size, halfSize, halfSize);
		// draws the current path with the current stroke style
		context.stroke();
	}

	/**
	 * Designs a dash into a tile section.
	 * 
	 * @param context context of canvas to design the shape
	 * @param size the size of tile, which is a square
	 * @param offsetX offset X where starts drawing
	 * @param offsetY offset Y where starts drawing
	 */
	void drawDash(Context2d context, int size, double offsetX, double offsetY) {
		// calculates half dimension
		final double halfSize = size / 2D;
		// calculates margin from boundary of tile
		final double margin = 2D;
		// draws shape
		// to point for drawing line
		context.moveTo(offsetX + margin, offsetY + margin);
		context.lineTo((halfSize - margin) + offsetX, (halfSize - margin) + offsetY);
	}
}
