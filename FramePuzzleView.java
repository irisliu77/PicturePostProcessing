package a7;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzleView extends JPanel implements KeyListener, MouseListener{
	private PictureView[][] tile_array = new PictureView[5][5];
	private PictureView picture_view ;
	private Picture picture;
	private Picture picture_display;
	private int total_width;
	private int total_height;
	private int tile_width;
	private int tile_height;
	private int blank_x;
	private int blank_y;
	
	public FramePuzzleView(Picture picture) {
		this.picture = picture;
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		total_width = picture.getWidth();
		total_height = picture.getHeight();
		tile_width = total_width / 5;
		tile_height = total_height / 5;
		
		Pixel[][] display_array = new Pixel[picture.getWidth()][picture.getHeight()];
		for(int i = 0; i < picture.getWidth(); i++) {
			for(int j = 0; j < picture.getHeight(); j++) {
				display_array[i][j] = new GrayPixel(1.0);
			}
		}
		picture_display = new MutablePixelArrayPicture(display_array,"");
		
		picture.paint(4 * tile_width - 1, 4 * tile_height - 1, total_width, total_height, new GrayPixel(1.0));
		picture_view = new PictureView(picture.createObservable());
		//picture_view.addKeyListener(this);
		//picture_view.addMouseListener(this);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				SubPicture temp = picture.extract(i * tile_width, j * tile_height, tile_width, tile_height);
				tile_array[i][j] = new PictureView(temp.createObservable());
			}
		}
		blank_x = 4;
		blank_y = 4;
		updatePicture();
		addKeyListener(this);
		setFocusable(true);
		add(picture_view, BorderLayout.CENTER);		
		
	}	
	
	private void updatePicture() {
		for(int i = 0;i < picture_display.getWidth(); i++) {
			for(int j = 0;j < picture_display.getHeight(); j++) {
				int current_x = i / tile_width;
				int current_y = j / tile_height;
				picture_display.paint(i, j, tile_array[current_x][current_y].getPicture().getPixel(i % tile_width, j % tile_height));
			}
		}
		picture_view = new PictureView(picture_display.createObservable());
		picture_view.addMouseListener(this);
		picture_view.addKeyListener(this);
		this.removeAll();
		this.add(picture_view);
		this.revalidate();
	}
	
	private void exchange(int og_blank_x, int og_blank_y, int new_blank_x,int new_blank_y) {
		PictureView temp = tile_array[og_blank_x][og_blank_y];
		tile_array[og_blank_x][og_blank_y] = tile_array[new_blank_x][new_blank_y];
		tile_array[new_blank_x][new_blank_y] = temp;
	}
	public void setPicture(ObservablePicture pic) {
		picture_view.setPicture(pic);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int destination_x = e.getX() / tile_width;
		int destination_y = e.getY() / tile_height;

		if(destination_x == blank_x) {
			
			if(destination_y < blank_y) {
				int diff = blank_y - destination_y;
				for(int i = 0;i < diff; i++) {
					exchange(blank_x, blank_y, blank_x, --blank_y);
					updatePicture();
				}
			}
			else if(destination_y > blank_y) {
				int diff = destination_y - blank_y;
				for(int i = 0;i < diff;i++) {
					exchange(blank_x, blank_y, blank_x, ++blank_y);
					updatePicture();
				}
			}
		}
		else if(destination_y == blank_y) {
			if(destination_x < blank_x) {
				int diff = blank_x - destination_x;
				for(int i = 0;i < diff; i++) {
					exchange(blank_x, blank_y, --blank_x, blank_y);
					updatePicture();
				}
			}
			else if(destination_x > blank_x) {
				int diff = destination_x - blank_x;
				for(int i = 0;i < diff; i++) {
					exchange(blank_x, blank_y, ++blank_x, blank_y);
					updatePicture();
				}
			}
		}
		//System.out.println(destination_x + " " + destination_y);		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(blank_y == 0) return;
			else {
				exchange(blank_x, blank_y, blank_x, --blank_y);
				updatePicture();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(blank_x == 0) return;
			else {
				exchange(blank_x, blank_y, --blank_x, blank_y);
				updatePicture();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(blank_y == 4) return;
			else {
				exchange(blank_x, blank_y, blank_x, ++blank_y);
				updatePicture();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(blank_x == 4) return;
			else {
				exchange(blank_x, blank_y, ++blank_x, blank_y);
				updatePicture();
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {	
		
	}
	
}
