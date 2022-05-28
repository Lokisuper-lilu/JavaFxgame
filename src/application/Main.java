package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	private Label label = new Label("轮到X下棋了");
	private int who = 1;
	private Qipan qipan[][] = new Qipan[3][3];

	public int isfull() {
		int c = 0;
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				if (qipan[i][j].Qizi != ' ') {
					c++;
				}
			}
		}
		if (c == 9) {
			return 1;
		} else
			return 0;
	}

	public int iswon() {
		int c = 0;
		char whoswon1 = 'X', whoswon2 = '0';
		for (int xh1 = 0; xh1 < 3; xh1++) {
			if (qipan[xh1][0].Qizi == whoswon1 && qipan[xh1][1].Qizi == whoswon1 && qipan[xh1][2].Qizi == whoswon1) {
				c = 1;
			} else if (qipan[0][xh1].Qizi == whoswon1 && qipan[1][xh1].Qizi == whoswon1
					&& qipan[2][xh1].Qizi == whoswon1) {
				c = 1;
			} else if (qipan[0][0].Qizi == whoswon1 && qipan[1][1].Qizi == whoswon1 && qipan[2][2].Qizi == whoswon1) {
				c = 1;
			} else if (qipan[0][2].Qizi == whoswon1 && qipan[1][1].Qizi == whoswon1 && qipan[2][0].Qizi == whoswon1) {
				c = 1;
			}
		}
		for (int xh1 = 0; xh1 < 3; xh1++) {
			if (qipan[xh1][0].Qizi == whoswon2 && qipan[xh1][1].Qizi == whoswon2 && qipan[xh1][2].Qizi == whoswon2) {
				c = 2;
			} else if (qipan[0][xh1].Qizi == whoswon2 && qipan[1][xh1].Qizi == whoswon2
					&& qipan[2][xh1].Qizi == whoswon2) {
				c = 2;
			} else if (qipan[0][0].Qizi == whoswon2 && qipan[1][1].Qizi == whoswon2 && qipan[2][2].Qizi == whoswon2) {
				c = 2;
			} else if (qipan[0][2].Qizi == whoswon2 && qipan[1][1].Qizi == whoswon2 && qipan[2][0].Qizi == whoswon2) {
				c = 2;
			}
		}
		return c;
	}

	public class Qipan extends Pane {
		private char Qizi = ' ';

		public Qipan() {
			setStyle("-fx-border-color: Black;-fx-border-wideth:10px");
			this.setPrefSize(500, 500);
			this.setOnMouseClicked(e -> {
				if (isfull() == 0 && who == 1 && this.Qizi == ' ' && iswon() == 0) {
					Line line1 = new Line(10, 10, this.getHeight() - 10, this.getWidth() - 10);
					Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
					this.getChildren().addAll(line1, line2);
					Qizi = 'X';
					who = 0;
					label.setText("轮到⚪下棋了");
					if(iswon()==1) {
						label.setText("X赢了");
					}
				} else if (isfull() == 0 && who == 0 && this.Qizi == ' ' && iswon() == 0) {
					Circle circle=new Circle(this.getHeight() / 2, this.getWidth() / 2, this.getHeight() / 2 - 2);
					circle.setFill(Color.YELLOWGREEN);
					this.getChildren().add(circle);
					Qizi = '0';
					who = 1;
					label.setText("轮到X下棋了");
					if(iswon()==2) {
						label.setText("⚪赢了");
					}
				}
			});
		}
	}

	@Override
	public void start(Stage primaryStage) {
		Button reset=new Button("重启游戏");
		BorderPane wholPane = new BorderPane();
		GridPane QIPane = new GridPane();
		try {
			for (int xh1 = 0; xh1 < 3; xh1++) {
				for (int xh2 = 0; xh2 < 3; xh2++) {
					QIPane.add(qipan[xh1][xh2] = new Qipan(), xh1, xh2);
				}
			}
		} catch (Exception e) {
			System.out.print('k');
		}
		reset.setOnAction(e->{
			for(int xh1=0;xh1<3;xh1++) {
				for(int xh2=0;xh2<3;xh2++) {
					qipan[xh1][xh2].Qizi=' ';
					qipan[xh1][xh2].getChildren().clear();
				}
			}
			label.setText("轮到X下棋了");
			who=1;
		});
		wholPane.setTop(reset);
		wholPane.setCenter(QIPane);
		label.setFont(Font.font("Times New Roman",FontWeight.BOLD,FontPosture.ITALIC,20));
		wholPane.setBottom(label);
		Scene scene = new Scene(wholPane, 600, 650);
		primaryStage.setScene(scene);
		primaryStage.setTitle("欢迎来到井字棋");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
