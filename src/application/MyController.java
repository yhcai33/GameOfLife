package application;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MyController implements Initializable {

	@FXML
	private Button myButton;

	@FXML
	private TextField myTextField;
	@FXML
	private Canvas myCanvas;
	@FXML
	private Text myText;
	private LifeGame game;
	private boolean AlternateFlag = false;
	private int fps=10;  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		GraphicsContext gc = myCanvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		
		this.game = new LifeGame(31,31);  //game initial
		this.game.randomSeed();
		
		Task task = new Task<Void>() {
			@Override
			public Void call() throws InterruptedException {

				while (!isCancelled()) {
					if (AlternateFlag) {
						this.cancel();
					}

					game.update();					
					myText.setText(game.blocks());							
					Thread.sleep(1000/fps);
				}
				return null;			}
		};
		new Thread(task).start();

	}

	@FXML
	private void show(ActionEvent e) {		
		System.out.println("Button Clicked!");		
		fps=Integer.valueOf(myTextField.getText());

	}
	@FXML 
	private void restarGame(ActionEvent e) {
		this.game.randomSeed();
		System.out.println("gameRestarted");
	}

}
