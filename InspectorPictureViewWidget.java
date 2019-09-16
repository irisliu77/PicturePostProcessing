package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InspectorPictureViewWidget extends JPanel implements MouseListener {

	private PictureView picture_view;
	private JLabel x_value;
	private JLabel y_value;
	private JLabel r_value;
	private JLabel g_value;
	private JLabel b_value;
	private JLabel brightness_value;
	private Picture picture;
	
	public InspectorPictureViewWidget(Picture picture) {
		setLayout(new BorderLayout());
		this.picture = picture;
		JPanel inspector_panel = new JPanel();
		inspector_panel.setLayout(new GridLayout(6,1));
		
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);
		
		//JLabel title_label = new JLabel(picture.getCaption());
	
		x_value = new JLabel(" X: 0 " );
		y_value = new JLabel(" Y: 0 " );
		r_value = new JLabel(" Red: 0.00 " );
		g_value = new JLabel(" Green: 0.00 " );
		b_value = new JLabel(" Blue: 0.00 " );
		brightness_value = new JLabel(" Brightness: 0.00 " );
		
		inspector_panel.add(x_value);
		inspector_panel.add(y_value);
		inspector_panel.add(r_value);
		inspector_panel.add(g_value);
		inspector_panel.add(b_value);
		inspector_panel.add(brightness_value);
		
		add(inspector_panel, BorderLayout.WEST);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		DecimalFormat df = new DecimalFormat("#0.00");
		double r = picture.getPixel(x, y).getRed();
		double g = picture.getPixel(x, y).getGreen();
		double b = picture.getPixel(x, y).getBlue();
		double brightness = picture.getPixel(x, y).getIntensity();
		x_value.setText(" X: " + x + " ");
		y_value.setText(" Y: " + y + " ");
		r_value.setText(" Red: " + df.format(r) + " ");
		g_value.setText(" Green: " + df.format(g) + " ");
		b_value.setText(" Blue: " + df.format(b) + " ");
		brightness_value.setText(" Brightness: " + df.format(brightness) + " ");
		
		//System.out.println("You clicked on the frame at: " + e.getX() + "," + e.getY());
		
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
	
}
