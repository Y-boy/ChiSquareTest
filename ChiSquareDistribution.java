import java.io.*;
// 通过对概率密度函数进行积分，得到概率分布
public class ChiSquareDistribution {
	public static void main(String[] args) {
		double division = 1000000000.0; 										// 积分的粒度
		double X_2_value = 11.0; 												// 积分到的最大卡方值
		long i = (long)(0.01 * division); 										// 积分的起始值
		double calculus = 0.079656;												// 卡方值为 0.01，自由度为 1，此时的卡方概率分布值
		for (; i <= division * X_2_value; i++) {
			calculus += (chisquare_distribution_density(i/division)/division);
			if (i % (long)(division/100) == 0) {
				System.out.println("X-2: " + i/division + ", P: " + calculus);
				output_chisquare_distribution_table(i/division + "," + calculus);
			}
		}
		System.out.println("Finished !!!");
	}

	public static double chisquare_distribution_density (double x) {			// 自由度为 1 时的卡方分布概率密度公式
		return Math.exp(-x/2.0) / Math.sqrt(2.0 * Math.PI * x);
	}

	public static void output_chisquare_distribution_table(String line) {		// 输出每个卡方分布的值到指定的表文件，主程序通过查表获得 P 值
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("chisquare-distribution-table.csv", true)) ;
			bw.write(line);
			bw.newLine();
			bw.flush();
			bw.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}