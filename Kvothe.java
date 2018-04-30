package Kvothe;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Kvothe - a robot by Ítalo
 */
public class Kvothe extends Robot
{
	/**
	 * run: Kvothe's default behavior
	 */
	public void run() {

		turnLeft(getHeading() % 90); // Calcula o necessário para ficar reto (0, 90, 180 ou 270 graus) usando como referência sua posição e gira o robô
		turnRadarRight(360); //Gira o radar por uma volta completa pra achar algum outro robô
		ahead(1000); //Avança por 1000 pixels
		while(true) { //Laço de repetição que dita a movimentação do robô
			ahead(800); //Avança por 1000 pixels
			turnRadarRight(360); //Gira o radar por uma volta completa pra achar algum outro robô
		}
	}
	public void onWin(WinEvent e) {
		turnRight(36000); //Gira o robô
	}	
	
	public void onHitWall(HitWallEvent e) {	
			turnLeft(getHeading() % 90); // Calcula o necessário para ficar reto (0, 90, 180 ou 270 graus) usando como referência sua posição
			turnRadarRight(360); //Gira o radar por uma volta completa pra achar algum outro robô
			ahead(800); //Avança por 1000 pixels
			turnRight(90); //Gira o robô
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double posicao = getHeading() + e.getBearing() - getGunHeading(); // Calcula a posição para tiro baseado na localição do outro robô e da sua posição do chassi e arma
		double tiro = ((getEnergy() - 20)/ 4); // Baseado em sua energia, calcula o poder do tiro a ser efetuado
		turnGunRight(posicao); // Vira a arma na direção calculada
		if (e.getBearing() > 250 || getEnergy() < 15 || e.getBearing() > getEnergy()){ //Se o outro robô estiver a mais de 250 pixels, minha energia for menor de 15 ou o "rumo" em graus do outros robô
          fire(2); // Atira com poder 2
       } else {
          fire(tiro); // Atira com a energia calculada anteriormente
       }
	}
}
