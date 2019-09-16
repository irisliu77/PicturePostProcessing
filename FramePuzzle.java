package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePuzzle {
	public static void main(String[] args) throws IOException {
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		p.setCaption("KMP in Namibia");
		ObservablePicture picture = p.createObservable(); 
		FramePuzzleView view = new FramePuzzleView(picture);
		//FramePuzzleModel model =  new FramePuzzleModel(picture);
		//FramePuzzleController puzzle_controller = new FramePuzzleController(view, model);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Frame Puzzle");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}
}