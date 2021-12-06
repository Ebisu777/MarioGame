/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2020 Raymond Buckley
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package fr.minesalbi.gsi.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Utils {
	public static final float ZOOM_MIN = 1.5f;

	/**
	 * Returns a value closer to a goal amount by the given increment.
	 */
	public static float approach(float start, float target, float increment) {
		increment = Math.abs(increment);
		if (start < target) {
			start += increment;

			if (start > target) {
				start = target;
			}
		} else {
			start -= increment;

			if (start < target) {
				start = target;
			}
		}
		return start;
	}

	public static boolean inside(float x, float y, float cx, float cy, float cwidth, float cheight) {
		float cxmin = cx - cwidth/2;
		float cxmax = cx + cwidth/2;
		float cymin = cy - cheight/2;
		float cymax = cy + cheight/2;

		if (x > cxmin && x < cxmax &&
				y > cymin && y < cymax)
			return true;

		return false;
	}
	
	public static float[] changeView(float[][] coordsPlayers, float cameraViewPortWidth, float cameraViewPortHeight) {
		float[] res = new float[4]; // cx, cy, cwidth, cheight
		float px1 = Float.MAX_VALUE; // xmin
		float px2 = Float.MIN_NORMAL; // xmax
		float py1 = Float.MAX_VALUE; // ymin
		float py2 = Float.MIN_VALUE; // ymax
		for(int i=0 ; i < coordsPlayers.length ; i++) {
			if (coordsPlayers[i][0]< px1) {
				px1 = coordsPlayers[i][0];
			}
			if (coordsPlayers[i][0] > px2) {
				px2 = coordsPlayers[i][0];
			}
			if (coordsPlayers[i][1] < py1) {
				py1 = coordsPlayers[i][1];
			}
			if (coordsPlayers[i][1] > py2) {
				py2 = coordsPlayers[i][1];
			}
		}
		
		float cx = (px1 + px2 + 300) / 2;
		float cy = (py1 + py2) / 2;
		float cwidth = Math.abs(px1 - px2)  + 300;
		float cheight = Math.abs(py1 - py2) + 300;
		float zoomW = (cwidth / cameraViewPortWidth) < 1 ? 1: (cwidth / cameraViewPortWidth);
		float zoomH = (cheight / cameraViewPortHeight) < 1 ? 1: (cheight / cameraViewPortHeight);
		res = new float[]{cx, cy, Math.max(zoomW, zoomH)};
		return res;
	}
	
	
	public static void adaptCamera(float[][] coordsPlayers, OrthographicCamera camera) {
		float xmin = Float.MAX_VALUE; 
		float xmax = Float.MIN_NORMAL;
		float ymin = Float.MAX_VALUE; 
		float ymax = Float.MIN_VALUE; 
		float cameraViewPortWidth = camera.viewportWidth;
		float cameraViewPortHeight = camera.viewportHeight;
		float x = camera.position.x;
		float y = camera.position.y;
		
		/* Retrieve bounding box of players i.e. min and max for their x and y */
		for(int i=0 ; i < coordsPlayers.length ; i++) {
			if (coordsPlayers[i][0]< xmin) {
				xmin = coordsPlayers[i][0];
			}
			if (coordsPlayers[i][0] > xmax) {
				xmax = coordsPlayers[i][0];
			}
			if (coordsPlayers[i][1] < ymin) {
				ymin = coordsPlayers[i][1];
			}
			if (coordsPlayers[i][1] > ymax) {
				ymax = coordsPlayers[i][1];
			}
		}
		
		float cx = (xmin + xmax + 300) / 2;
		float cy = (ymin + ymax) / 2;
		float cwidth = Math.abs(xmin - xmax)  + 300;
		float cheight = Math.abs(ymin - ymax) + 300;
		float zoomW = (cwidth / cameraViewPortWidth) < ZOOM_MIN ? ZOOM_MIN: (cwidth / cameraViewPortWidth);
		float zoomH = (cheight / cameraViewPortHeight) < ZOOM_MIN ? ZOOM_MIN: (cheight / cameraViewPortHeight);
		
		
		camera.translate(cx - x , cy - y, 0);
		camera.zoom = Math.max(zoomW, zoomH);
	}

}
