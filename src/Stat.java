import java.util.Random;

public class Stat {
    public double min, max, mean;

    private Random r = new Random();

    public Stat(double min, double max, double mean){
        this.min = min;
        this.max = max;
        this.mean = mean;
    }

    /**
     * Generates a random number from a skewed normal distribution
     * @return
     */
    public double random() {
        double range = max - min;
        if(range == 0) return min;
        
        double skew = 1;
        double mid = min + range / 2;
        double bias = 2.0 * (mean - mid) / max;
        double unitGaussian = r.nextGaussian();
        double biasFactor = Math.exp(bias);
        double value = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
        return Math.max(min, Math.min(max, value));
        // return value;
    }

    // public static void main(String[] args) {
    //     Stat s = new Stat(0, 100, 100);
    //     int count = 100000;
    //     int bins = 10;
    //     int maxLength = 200;
    //     int[] f = new int[3 * bins];
    //     System.out.println("Bias: " + (2.0 * (s.mean - ((s.max - s.min) / 2)) / (s.max)));

    //     for(int i = 0; i < count; i++){
    //         int value = s.random();
    //         f[(value - s.min) / bins]++;
    //     }
    //     for(int i = 0; i < f.length; i++){
    //         int length = f[i] * maxLength / count;
    //         System.out.print(String.format("%4s: ", "" + (i * bins + s.min)));
    //         for(int j = 0; j < length; j++) System.out.print("x");
    //         System.out.println();
    //     }
    // }
}
