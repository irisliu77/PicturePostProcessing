package a7;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterController implements ChangeListener{
	private ImageAdjusterView view;
	private ImageAdjusterModel model;
	
	public ImageAdjusterController(ImageAdjusterModel model, ImageAdjusterView view) {
		this.model = model;
		this.view = view;
		view.addChangeListener(this);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider)e.getSource();
		
		int value = slider.getValue();

		switch(slider.getName()) {
		case "blur_slider":
			model.setBlurValue(value);
			break;
		case "saturation_slider":
			model.setSaturationValue(value);
			break;
		case "brightness_slider":
			model.setBrightenValue(value);
			break;
		default:
			break;
		}
		
	}
	
}
