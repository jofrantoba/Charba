package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLine;

public final class DefaultLine extends DefaultArc implements IsDefaultLine{
	
	// default line tension
	private static final float DEFAULT_TENSION = 0.4F;
	// default background color
	private static final String DEFAULT_BACKGROUND_COLOR = "rgba(0,0,0,0.1)";
	// default border width
	private static final int DEFAULT_BORDER_WIDTH = 3;
	// default line tension
	private static final String DEFAULT_BORDER_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_BORDER_DASH_OFFSET = 0;

	private static final boolean DEFAULT_CAP_BEZIER_POINTS = true;
	
	private static final boolean DEFAULT_STEPPED = false;
	
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
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#getTension()
	 */
	@Override
	public double getTension() {
		return DEFAULT_TENSION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#getBorderCapStyle()
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return CapStyle.butt;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#getBorderDashOffset()
	 */
	@Override
	public int getBorderDashOffset() {
		return DEFAULT_BORDER_DASH_OFFSET;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#getBorderJoinStyle()
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return JoinStyle.miter;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#isCapBezierPoints()
	 */
	@Override
	public boolean isCapBezierPoints() {
		return DEFAULT_CAP_BEZIER_POINTS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#getFill()
	 */
	@Override
	public Fill getFill() {
		return Fill.origin;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.elements.line.IsDefaultLine#isStepped()
	 */
	@Override
	public boolean isStepped() {
		return DEFAULT_STEPPED;
	}

}