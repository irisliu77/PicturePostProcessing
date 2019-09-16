package a7;

import java.util.ArrayList;
import java.util.List;

public class ImageAdjusterModel {
	private Picture picture;
	private int blur_size;
	private int saturate_value;
	private int brighten_value;
	private Pixel[][] parray;
	private Pixel[][] og_array;
	
	public ImageAdjusterModel(Picture picture) {
		this.picture = picture;
		og_array = findArray(picture);
	}
	
	public Pixel[][] findArray(Picture picture) {
		Pixel[][] og_array = new Pixel [picture.getWidth()][picture.getHeight()];
		for(int i = 0; i < picture.getWidth(); i++) {
			for(int j = 0; j < picture.getHeight(); j++) {
				og_array[i][j] = picture.getPixel(i, j);
			}
		}
		return og_array;
	}
	public void setBlurValue(int value) {
		blur_size = value;
		repaintPicture();
		return;
	}
	
	public void setSaturationValue(int value) {
		saturate_value = value;
		repaintPicture();
		return;
	}
	
	public void setBrightenValue(int value) {
		brighten_value = value;
		repaintPicture();
		return;
	}
	
	public Pixel blurHelper(List<Pixel> p_list) {
		double sum_r = 0.0;
		double sum_g = 0.0;
		double sum_b = 0.0;
		double r, g, b;
		for(Pixel p : p_list) {
			sum_r += p.getRed();
			sum_g += p.getGreen();
			sum_b += p.getBlue();
		}
		r = sum_r / p_list.size();
		g = sum_g / p_list.size();
		b = sum_b / p_list.size();
		return new ColorPixel(r, g, b);
	}
	
	public void blurPixel() {
		for(int i = 0; i < picture.getWidth(); i++) {
			for(int j = 0; j < picture.getHeight(); j++) {
				List<Pixel> p_list = new ArrayList<Pixel>();
				for(int x = i - blur_size; x <= (i + blur_size); x++) {
					for(int y = j - blur_size; y <= (j + blur_size); y++) {
						if(0 <= x && x < picture.getWidth() &&
						   0 <= y && y < picture.getHeight()) {
							p_list.add(og_array[x][y]);
						}
					}
				}
				parray[i][j] = blurHelper(p_list);
			}
		}
	}
	
	public void brightenPixel() {
		for(int i = 0; i < picture.getWidth(); i++) {
			for(int j = 0; j < picture.getHeight(); j++) {
				if(brighten_value >= 0) {
					parray[i][j] = parray[i][j].lighten((double)brighten_value / 100.0);
				}
				else {
					parray[i][j] = parray[i][j].darken((double)-brighten_value / 100.0);
				}
			}
		}
	}
	
	public Pixel negativeSaturationHelper(int saturate_value, Pixel p) {
		double i = p.getIntensity();
		double r = p.getRed() * (1.0 + ((double)saturate_value / 100.0) ) - (i * (double)saturate_value / 100.0);
		double g = p.getGreen() * (1.0 + ((double)saturate_value / 100.0) ) - (i * (double)saturate_value / 100.0);
		double b = p.getBlue() * (1.0 + ((double)saturate_value / 100.0) ) - (i * (double)saturate_value / 100.0);
		return new ColorPixel(r, g, b);
	}
	
	public Pixel positiveSaturationHelper(int saturate_value, Pixel p) {
		double lc = Math.max(p.getRed(), p.getGreen());
		lc = Math.max(lc, p.getBlue());
		
		if(lc <= 0.01) return Pixel.BLACK;
		
		double r = p.getRed() * ((lc + ((1.0 - lc) * ((double)saturate_value / 100.0))) / lc);
		double g = p.getGreen() * ((lc + ((1.0 - lc) * ((double)saturate_value / 100.0))) / lc);
		double b = p.getBlue() * ((lc + ((1.0 - lc) * ((double)saturate_value / 100.0))) / lc);
		return new ColorPixel(r, g, b);
	}
	
	public void saturatePixel() {
		for(int i = 0; i <  picture.getWidth(); i++) {
			for(int j = 0; j < picture.getHeight(); j++) {
				if(saturate_value >= 0) {
					parray[i][j] = positiveSaturationHelper(saturate_value, parray[i][j]);
				}
				else {
					parray[i][j] = negativeSaturationHelper(saturate_value, parray[i][j]);
				}
			}
		}
	}
	
	public void repaintPicture() {
		parray = new Pixel[picture.getWidth()][picture.getHeight()];
		blurPixel();
		saturatePixel();
		brightenPixel();
		picture.paint(0, 0, new MutablePixelArrayPicture(parray,""));
	}
}