package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines;

public class DefaultAngleLines implements IsDefaultAngleLines {
	
	private static final boolean DEFAULT_DISPLAY = true;

	private static final int DEFAULT_LINE_WIDTH = 1;


	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines#getColor()
	 */
	@Override
	public String getColorAsString() {
		return DefaultOptions.get().getDefaultColorAsString();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines#getLineWidth()
	 */
	@Override
	public int getLineWidth() {
		return DEFAULT_LINE_WIDTH;
	}

}