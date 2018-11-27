package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;

public final class DefaultPointLabels extends AbstractDefaultFontItem implements IsDefaultPointLabels {
	
	private static final boolean DEFAULT_DISPLAY = true;

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return DEFAULT_DISPLAY;
	}

	
}