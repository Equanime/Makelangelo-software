package com.marginallyclever.converters;

import java.io.IOException;
import java.io.Writer;

import com.jogamp.opengl.GL2;
import com.marginallyclever.basictypes.ImageManipulator;
import com.marginallyclever.basictypes.TransformedImage;
import com.marginallyclever.makelangelo.DrawPanelDecorator;
import com.marginallyclever.makelangeloRobot.MakelangeloRobotSettings;

/**
 * Converts a BufferedImage to gcode
 * @author danroyer
 *
 */
public abstract class ImageConverter extends ImageManipulator implements DrawPanelDecorator {
	/**
	 * @param img the <code>java.awt.image.BufferedImage</code> this filter is using as source material.
	 * @return true if conversion succeeded.
	 */
	public boolean convert(TransformedImage img,Writer out) throws IOException {
		return false;
	}


	/**
	 * insert the machine-specific preamble at the start of the gcode file.
	 * @param img
	 * @param out
	 * @throws IOException
	 */
	protected void imageStart(Writer out) throws IOException {
		tool = machine.getCurrentTool();

		out.write(machine.getConfigLine() + ";\n");
		out.write(machine.getBobbinLine() + ";\n");
		out.write(machine.getSetStartAtHomeLine() + ";\n");
		tool.writeChangeTo(out);

		previousX = 0;
		previousY = 0;

		setAbsoluteMode(out);
	}

	
	@Override
	public void render(GL2 gl2,MakelangeloRobotSettings settings) {}
}