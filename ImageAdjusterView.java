package a7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

public class ImageAdjusterView extends JPanel {

	private PictureView picture_view;
	private JSlider blur_slider;
	private JSlider saturation_slider;
	private JSlider brightness_slider;
	private JLabel blur;
	private JLabel saturation;
	private JLabel brightness;
	private JPanel row1;
	private JPanel row2;
	private JPanel row3;
	private ChangeListener blur_listener;
	private ChangeListener saturation_listener;
	private ChangeListener brightness_listener;
	
	public ImageAdjusterView(ObservablePicture picture) {
		setLayout(new BorderLayout());
		//initialize sliders
		blur_slider = new JSlider(0, 5, 0);
		blur_slider.setMajorTickSpacing(1);
		blur_slider.setPaintTicks(true);
		blur_slider.createStandardLabels(1);
		blur_slider.setPaintLabels(true);
		blur_slider.setName("blur_slider");
		
		saturation_slider = new JSlider(-100, 100, 0);
		saturation_slider.setMajorTickSpacing(25);
		saturation_slider.setPaintTicks(true);
		saturation_slider.createStandardLabels(25);
		saturation_slider.setPaintLabels(true);
		saturation_slider.setName("saturation_slider");
		
		brightness_slider = new JSlider(-100, 100, 0);
		brightness_slider.setMajorTickSpacing(25);
		brightness_slider.setPaintTicks(true);
		brightness_slider.createStandardLabels(25);
		brightness_slider.setPaintLabels(true);
		brightness_slider.setName("brightness_slider");
		//set the size of the slider
		Dimension slider_dim1 = new Dimension(300,50);
		Dimension slider_dim2 = new Dimension(340,50);
		blur_slider.setPreferredSize(slider_dim1);
		saturation_slider.setPreferredSize(slider_dim2);
		brightness_slider.setPreferredSize(slider_dim2);
		
		//initialize names of different functions
		blur = new JLabel("Blur: ");
		saturation = new JLabel("Saturation: ");
		brightness = new JLabel("Brightness: ");
		
		//set the size of the name panels
		Dimension panel_dim1 = new Dimension(600, 50);
		Dimension panel_dim2 = new Dimension(560, 50);
		blur_slider.setPreferredSize(panel_dim1);
		saturation_slider.setPreferredSize(panel_dim2);
		brightness_slider.setPreferredSize(panel_dim2);
		
		//combine correspondant slider and label
		row1 = new JPanel();
		row1.add(blur, BorderLayout.WEST);
		row1.add(blur_slider, BorderLayout.EAST);
		row2 = new JPanel();
		row2.add(saturation, BorderLayout.WEST);
		row2.add(saturation_slider, BorderLayout.EAST);
		row3 = new JPanel();
		row3.add(brightness, BorderLayout.WEST);
		row3.add(brightness_slider, BorderLayout.EAST);
		
		//the overall panel for adjuster
		JPanel adjuster_panel = new JPanel();
		adjuster_panel.setLayout(new GridLayout(3,1));
		adjuster_panel.add(row1);
		adjuster_panel.add(row2);
		adjuster_panel.add(row3);
		
		picture_view = new PictureView(picture);
		//picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);
		add(adjuster_panel, BorderLayout.SOUTH);
	}
	
	public void addChangeListener(ChangeListener cl) {
		blur_slider.addChangeListener(cl);
		saturation_slider.addChangeListener(cl);
		brightness_slider.addChangeListener(cl);
	}
	

	public void setPicture(ObservablePicture pic) {
		picture_view.setPicture(pic);
	}
	

}
