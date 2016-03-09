/**
 * @author David Han & Keith Davis
 */
package recursiveart;
import java.awt.*;

import processing.core.PApplet;
/*
 * Recursive Art
 * 
 * This program draws some pretty pictures
 * using recursion
 */

public class RecursiveArt extends PApplet {

	/*
	 * The setup sets the initial size, adds smoothing (for pixelated lines) and
	 * noStroke, which takes out outlines of shapes. This method is called once
	 * - when the program starts.
	 */
	public void setup() {
		size(800, 800);
		smooth();
		noStroke();
	}

	/*
	 * The draw program is called many times a second. It draws shapes to the
	 * screen.
	 * 
	 * The art should still be correct when the window is resized.
	 */
	public void draw() {
		// Draw background first.
		// width is 800, height is 738
		noStroke();
		background(255, 255, 255);
		drawTarget(width / 2, height / 2, 1);
		rotatedSquare(width / 2, 0, width / 2, height / 2);
		fill(0,0,255);
		rect(0,height/2,width/2,height/2);
		drawTriangle(0,height/2,width/2,height/2);
		drawBox(width/2+width/4,height/2+height/8,width/4,height/4,1);
		
	}
/**
 * Draws concentric circles
 * @param w - width of ellipse
 * @param h - height of ellipse
 * @param color - number that changes signs each iteration to allow for different 
 * color of fill
 */
	public void drawTarget(float w, float h, int color) {
		if (color > 0) {
			fill(255, 0, 0);
		} else {
			fill(0, 0, 255);
		}
		if (w > 2 && h > 2) {
			ellipse(width / 4, height / 4, w, h);
			color = color * -1;
			drawTarget(w - 50, h - 50, color);
		}
	}
	/**
	 * Draws a dreamcatcher. First draws a square. Then draws a diamond using the 
	 * midpoints of the four sides of the square
	 * @param x - x coordinate of anchor point of square.
	 * @param y - y coordinate of anchor point of square
	 * @param wx - width of square
	 * @param hy - height of square
	 */
	public void rotatedSquare(int x, int y, int wx, int hy) {
		if (wx>2 || hy>2) {
			int midpointw= (x+(x+wx))/2;
			int midpointh=(y+(y+hy))/2;
			fill(153, 153, 255);
			rect(x, y, wx, hy);
			fill(255, 255, 255);
			quad(midpointw, y, 
					x+wx, midpointh, 
					midpointw,hy+y,
					x, midpointh);
			rotatedSquare(x+ wx/4,y+ hy/4,wx/2,hy/2);
		}
	}
	/**
	 * Draws Sierpinski's Triangle. First recursion draws smaller triangles towards the top right corner of drawing space.
	 * Next recursion draws adjacent triangle placed below the triangle drawn from the first recursion.
	 * Last recursion draws adjacent triangle placed to the left of the triangle drawn from the first recursion.
	 * @param x - x-coordinate of the top corner of right triangle
	 * @param y - y-coordinate of the top corner of right triangle
	 * @param w - width of triangle
	 * @param h - height of triangle
	 */
	public void drawTriangle(int x, int y, int w,int h){
		if(w>3){
			fill(255,255,0);
			triangle(x,y,x,y+h,x+w,y+h);
			drawTriangle(x+w/2,y,w/2,h/2);
			drawTriangle(x-w/2,y,w/2,h/2);
			drawTriangle(x+w/2,y+h,w/2,h/2);
		}
	}
	/**
	 * Draws pyramid of cubes.
	 * @param x - x coordinate of top corner of diamond
	 * @param y - y coordinate of top corner of diamond
	 * @param w - width of square
	 * @param h - height of square
	 * @param color - number that changes signs to allow for different color of fill
	 */
	public void drawBox(int x, int y, int w, int h,int color){
		if(w>20){
			stroke(255,255,255);		
			fill(87,87,87);
			quad(
					x,y+h,//top left corner
					x,y+h+h/2,//top right corner
					x+w/2,y+h/2+h/2,//bottom right corner
					x+w/2,y+h/2//bottom left corner
					);
			quad(
					x-w/2,y+h/2,//top left corner
					x,y+h,//top right corner
					x,y+h+h/2,//bottom right corner
					x-w/2,y+h);//bottom left corner		
			if(color>0){
				fill(0,0,255);
				}else{fill(255,0,0);}
				//top face
				quad(	x,y,//top corner
						x+w/2,y+h/2,//right corner
						x,y+h,//bottom corner
						x-w/2,y+h/2);//left corner	
			drawBox(x,y-h/4,w/2,h/2,color*-1);
			drawBox(x+w/2,y+h/2+h/4,w/2,h/2,color*-1);
			drawBox(x-w/2,y+h/2+h/4,w/2,h/2,color*-1);
		}		
	}

}
