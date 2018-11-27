package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultRectangle;

public final class DefaultRectangle extends DefaultArc implements IsDefaultRectangle{
	
	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border with
	private static final int DEFAULT_BORDER_WIDTH = 0;
	// default border color
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBackgroundColor()
	 */
	@Override
	public String getBackgroundColorAsString() {
		return DEFAULT_BACKGROUND_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBorderWidth()
	 */
	@Override
	public int getBorderWidth() {
		return DEFAULT_BORDER_WIDTH;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.DefaultArc#getBorderColor()
	 */
	@Override
	public String getBorderColorAsString() {
		return DEFAULT_BORDER_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.rectangle.IsDefaultRectangle#getBorderSkipped()
	 */
	@Override
	public Position getBorderSkipped() {
		return Position.bottom;
	}

}