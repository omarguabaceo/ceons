package mtk.eon.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import mtk.geom.Vector2F;
import mtk.utilities.DashedDrawing;

public class Node extends Figure {
	
	private static LinearGradient nodeFill = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,	new Stop[] {new Stop(0, Color.MAGENTA), new Stop(0.5f, Color.PURPLE)});
	
	static float imageSize = 48; //24

	public Node(Node node) {
		startPoint = node.startPoint.clone();
		name = node.name;
		loadImage();
	}

	public Node(Vector2F startPoint, int number) {

		super(new Vector2F(startPoint.getX() - imageSize / 2, startPoint.getY()
				- imageSize / 2), "Node" + number);
		loadImage();
	}

	public Node(Vector2F startPoint, String _name) {
		super(new Vector2F(startPoint.getX() - imageSize / 2, startPoint.getY() - imageSize / 2), _name);
		loadImage();
	}

	@Override
	public void draw(GraphicsContext gc) {
		// loadImage();
		//gc.drawImage(image, startPoint.getX(), startPoint.getY());
		gc.setFill(Color.PINK);
		gc.fillOval(startPoint.getX() - imageSize / 16f, startPoint.getY() - imageSize / 16f, imageSize + imageSize / 8f, imageSize + imageSize / 8f);
		gc.setFill(nodeFill);
		gc.fillOval(startPoint.getX(), startPoint.getY(), imageSize, imageSize);
		gc.setFill(Color.WHITE);
		gc.fillOval(startPoint.getX() + imageSize / 8f, startPoint.getY() + imageSize / 8f, imageSize - imageSize / 4f, imageSize - imageSize / 4f);
	}

	@Override
	public boolean equals(Object obj) {
		Node temp = (Node) obj;
		return (temp.getStartPoint().equals(startPoint) && temp.name
				.equals((name)));
	}

	protected void loadImage() {
		image = new Image("mtk/eon/drawing/circle_image.jpg", imageSize,
				imageSize, true, false);
	}

	public Vector2F getCenterPoint() {
		float x = startPoint.getX() + imageSize / 2;
		float y = startPoint.getY() + imageSize / 2;
		return new Vector2F(x, y);
	}

	@Override
	protected void drawOutline(GraphicsContext gc, Color color) {
		DashedDrawing.drawDashedCircle(gc, new Vector2F(startPoint.getX()
				+ imageSize / 2, startPoint.getY() + imageSize / 2),
				imageSize / 2 + 3, Color.GRAY);
	}

	public static void changeNodeSize(float factory) {
		System.out.println("factory" + factory);
		restoreDefaultNodeSize();
		imageSize = (int) (imageSize * factory);
		if (imageSize < 12)
			imageSize = 12;
		else if (imageSize > 24)
			imageSize = 24;

	}

	public static void restoreDefaultNodeSize() {
		imageSize = 24;
	}

	public static float getNodeSize() {
		return imageSize;
	}

	@Override
	protected double calculateDistanceFromPoint(Vector2F p) {
		return Math.sqrt(Math.pow(p.getX() - startPoint.getX(), 2)
				+ Math.pow(p.getY() - startPoint.getY(), 2));
	}
}