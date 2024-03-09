package mowitnow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tondeuse {

	private int maxX;
	private int maxY;

	public Tondeuse(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

	public void executeInstructions(String[] initialPosition, String[] instructions) {
		int x = Integer.parseInt(initialPosition[0]);
		int y = Integer.parseInt(initialPosition[1]);
		char orientation = initialPosition[2].charAt(0);

		for (int i = 0; i < instructions.length; i++) {
			char instruction = instructions[i].charAt(0);
			switch (instruction) {
			case 'D':
				orientation = turnRight(orientation);
				break;
			case 'G':
				orientation = turnLeft(orientation);
				break;
			case 'A':
				int[] newPosition = moveForward(x, y, orientation);
				if (isValidPosition(newPosition[0], newPosition[1])) {
					x = newPosition[0];
					y = newPosition[1];
				}
				break;
			default:
				break;
			}
		}
		System.out.println(x + " " + y + " " + orientation);
	}

	private char turnRight(char orientation) {
		switch (orientation) {
		case 'N':
			return 'E';
		case 'E':
			return 'S';
		case 'S':
			return 'W';
		case 'W':
			return 'N';
		default:
			return orientation;
		}
	}

	private char turnLeft(char orientation) {
		switch (orientation) {
		case 'N':
			return 'W';
		case 'W':
			return 'S';
		case 'S':
			return 'E';
		case 'E':
			return 'N';
		default:
			return orientation;
		}
	}

	private int[] moveForward(int x, int y, char orientation) {
		switch (orientation) {
		case 'N':
			return new int[] { x, y + 1 };
		case 'E':
			return new int[] { x + 1, y };
		case 'S':
			return new int[] { x, y - 1 };
		case 'W':
			return new int[] { x - 1, y };
		default:
			return new int[] { x, y };
		}
	}

	private boolean isValidPosition(int x, int y) {
		return x >= 0 && x <= maxX && y >= 0 && y <= maxY;
	}

	public static void main(String[] args) {
		try {
			File file = new File("../input.txt");
			Scanner scanner = new Scanner(file);

			int maxX = scanner.nextInt();
			int maxY = scanner.nextInt();
			scanner.nextLine(); // Move scanner to next line

			Tondeuse tondeuse = new Tondeuse(maxX, maxY);

			while (scanner.hasNextLine()) {
				String[] position = scanner.nextLine().split(" ");
				String instructions = scanner.nextLine();

				tondeuse.executeInstructions(position, instructions.split(""));
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
