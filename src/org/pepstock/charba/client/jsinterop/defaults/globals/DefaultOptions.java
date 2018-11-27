package org.pepstock.charba.client.jsinterop.defaults.globals;

import org.pepstock.charba.client.enums.FontStyle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAnimation;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultElements;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultHover;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLayout;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultLegend;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTitle;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTooltips;
import org.pepstock.charba.client.jsinterop.utils.Window;

public final class DefaultOptions implements IsDefaultOptions{
	
	private static final DefaultOptions INSTANCE = new DefaultOptions();
	
	// default values
	private static final boolean DEFAULT_RESPONSIVE = true;

	private static final int DEFAULT_RESPONSIVE_ANIMATION_DURATION = 0;

	private static final boolean DEFAULT_MAINTAIN_ASPECT_RATIO = true;

	private static final String DEFAULT_COLOR = "rgba(0,0,0,0.1)";

	private static final int DEFAULT_FONT_SIZE = 12;

	private static final String DEFAULT_FONT_COLOR = "#666";

	private static final String DEFAULT_FONT_FAMILY = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";

	private static final boolean DEFAULT_SHOW_LINES = true;

	private static final boolean DEFAULT_SPAN_GAPS = false;

	private static final double DEFAULT_CUTOUT_PERCENTAGE = 0D;

	private static final double DEFAULT_ROTATION = -0.5 * Math.PI;

	private static final double DEFAULT_CIRCUMFERENCE = 2 * Math.PI;

	private static final double DEFAULT_START_ANGLE = -0.5 * Math.PI;
	
	private final DefaultAnimation animation = new DefaultAnimation();
	
	private final DefaultHover hover = new DefaultHover();
	
	private final DefaultElements elements = new DefaultElements();
	
	private final DefaultLayout layout = new DefaultLayout();
	
	private final DefaultTitle title = new DefaultTitle();
	
	private final DefaultLegend legend = new DefaultLegend();
	
	private final DefaultTooltips tooltips = new DefaultTooltips();
	
	private final DefaultScale scale = new DefaultScale();

	/**
	 * 
	 */
	protected DefaultOptions() {
	}
	
	public static final DefaultOptions get() {
		return INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getScale()
	 */
	@Override
	public IsDefaultScale getScale() {
		return scale;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getAnimation()
	 */
	@Override
	public IsDefaultAnimation getAnimation() {
		return animation;
	}


	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getLayout()
	 */
	@Override
	public IsDefaultLayout getLayout() {
		return layout;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultOptions#getElements()
	 */
	@Override
	public IsDefaultElements getElements() {
		return elements;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getHover()
	 */
	@Override
	public IsDefaultHover getHover() {
		return hover;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getTitle()
	 */
	@Override
	public IsDefaultTitle getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getLegend()
	 */
	@Override
	public IsDefaultLegend getLegend() {
		return legend;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.IsDefaultOptions#getTooltips()
	 */
	@Override
	public IsDefaultTooltips getTooltips() {
		return tooltips;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isResponsive()
	 */
	@Override
	public boolean isResponsive() {
		return DEFAULT_RESPONSIVE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getResponsiveAnimationDuration()
	 */
	@Override
	public int getResponsiveAnimationDuration() {
		return DEFAULT_RESPONSIVE_ANIMATION_DURATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isMaintainAspectRatio()
	 */
	@Override
	public boolean isMaintainAspectRatio() {
		return DEFAULT_MAINTAIN_ASPECT_RATIO;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDevicePixelRatio()
	 */
	@Override
	public double getDevicePixelRatio() {
		return Window.getDevicePixelRatio();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultColor()
	 */
	@Override
	public String getDefaultColorAsString() {
		return DEFAULT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontColor()
	 */
	@Override
	public String getDefaultFontColorAsString() {
		return DEFAULT_FONT_COLOR;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontSize()
	 */
	@Override
	public int getDefaultFontSize() {
		return DEFAULT_FONT_SIZE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontStyle()
	 */
	@Override
	public FontStyle getDefaultFontStyle() {
		return FontStyle.normal;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getDefaultFontFamily()
	 */
	@Override
	public String getDefaultFontFamily() {
		return DEFAULT_FONT_FAMILY;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isShowLines()
	 */
	@Override
	public boolean isShowLines() {
		return DEFAULT_SHOW_LINES;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#isSpanGaps()
	 */
	@Override
	public boolean isSpanGaps() {
		return DEFAULT_SPAN_GAPS;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getCutoutPercentage()
	 */
	@Override
	public double getCutoutPercentage() {
		return DEFAULT_CUTOUT_PERCENTAGE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getRotation()
	 */
	@Override
	public double getRotation() {
		return DEFAULT_ROTATION;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getCircumference()
	 */
	@Override
	public double getCircumference() {
		return DEFAULT_CIRCUMFERENCE;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.options.DefaultOptions#getStartAngle()
	 */
	@Override
	public double getStartAngle() {
		return DEFAULT_START_ANGLE;
	}
}