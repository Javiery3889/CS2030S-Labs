import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 1, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Javier Yong 
 */

class Lab0 {
    
    /**
    * Calculate estimated of PI using Monte Carlo Method. Takes in a int
    * numOfPoints and a int seed.
    *
    * @param numOfPoints The number of points to randomly generate.
    * @param seed The seed to be used for the PRNG.
    *
    * @return The double representing the estimated value of PI.
    */
    private static double estimatePi(int numOfPoints, int seed) {
        double n = 0;
        double k = numOfPoints;
        Circle c = new Circle(new Point(0.5,0.5),0.5);
        RandomPoint.setSeed(seed);

        for (int i = 0; i < numOfPoints; i++) {
           Point randomPoint = new RandomPoint(0,1,0,1);
            n += c.contains(randomPoint) ? 1 : 0;
        }
        return 4 * n / k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfPoints = sc.nextInt();
        int seed = sc.nextInt();

        double pi = estimatePi(numOfPoints, seed);

        System.out.println(pi);
        sc.close();
    }
}
