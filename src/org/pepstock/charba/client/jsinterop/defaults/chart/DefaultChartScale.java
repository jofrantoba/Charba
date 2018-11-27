package org.pepstock.charba.client.jsinterop.defaults.chart;

import org.pepstock.charba.client.enums.AxisType;
import org.pepstock.charba.client.enums.Position;
import org.pepstock.charba.client.enums.ScaleBounds;
import org.pepstock.charba.client.enums.ScaleDistribution;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultAngleLines;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultGridLines;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultPointLabels;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultScaleLabel;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTicks;
import org.pepstock.charba.client.jsinterop.defaults.IsDefaultTime;
import org.pepstock.charba.client.jsinterop.options.Scale;

public final class DefaultChartScale implements IsDefaultScale{
	
	private final Scale scale;
	
	private final DefaultChartScaleLabel scaleLabel;
	
	private final DefaultChartTicks ticks;
	
	private final DefaultChartGridLines gridLines;
	
	private final DefaultChartAngleLines angleLines;
	
	private final DefaultChartPointLabels pointLabels;
	
	private final DefaultChartTime time;

	/**
	 * @param scale
	 */
	public DefaultChartScale(Scale scale) {
		this.scale = scale;
		this.scaleLabel = new DefaultChartScaleLabel(scale.getScaleLabel());
		this.ticks = new DefaultChartTicks(scale.getTicks());
		this.gridLines=  new DefaultChartGridLines(scale.getGrideLines());
		this.angleLines = new DefaultChartAngleLines(scale.getAngleLines());
		this.pointLabels = new DefaultChartPointLabels(scale.getPointLabels());
		this.time = new DefaultChartTime(scale.getTime());
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getScaleLabel()
	 */
	@Override
	public IsDefaultScaleLabel getScaleLabel() {
		return scaleLabel;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getTicks()
	 */
	@Override
	public IsDefaultTicks getTicks() {
		return ticks;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getGrideLines()
	 */
	@Override
	public IsDefaultGridLines getGrideLines() {
		return gridLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getAngleLines()
	 */
	@Override
	public IsDefaultAngleLines getAngleLines() {
		return angleLines;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getPointLabels()
	 */
	@Override
	public IsDefaultPointLabels getPointLabels() {
		return pointLabels;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getTime()
	 */
	@Override
	public IsDefaultTime getTime() {
		return time;
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isStacked()
	 */
	@Override
	public boolean isStacked() {
		return scale.isStacked();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getType()
	 */
	@Override
	public AxisType getType() {
		return scale.getType();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getWeight()
	 */
	@Override
	public int getWeight() {
		return scale.getWeight();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isDisplay()
	 */
	@Override
	public boolean isDisplay() {
		return scale.isDisplay();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#isOffset()
	 */
	@Override
	public boolean isOffset() {
		return scale.isOffset();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getPosition()
	 */
	@Override
	public Position getPosition() {
		return scale.getPosition();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBarPercentage()
	 */
	@Override
	public double getBarPercentage() {
		return scale.getBarPercentage();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getCategoryPercentage()
	 */
	@Override
	public double getCategoryPercentage() {
		return scale.getCategoryPercentage();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBarThickness()
	 */
	@Override
	public int getBarThickness() {
		return scale.getBarThickness();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getMaxBarThickness()
	 */
	@Override
	public int getMaxBarThickness() {
		return scale.getMaxBarThickness();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getDistribution()
	 */
	@Override
	public ScaleDistribution getDistribution() {
		return scale.getDistribution();
	}

	/* (non-Javadoc)
	 * @see org.pepstock.charba.client.jsinterop.defaults.IsDefaultScale#getBounds()
	 */
	@Override
	public ScaleBounds getBounds() {
		return scale.getBounds();
	}

}