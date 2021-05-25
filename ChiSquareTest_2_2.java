import java.io.*;
import java.util.*;

public class ChiSquareTest_2_2 {
	static Scanner sc = new Scanner(System.in);
	public static void main (String[] args) {							// 入参是 4 格表的逐行的数据
		double A1 = Double.parseDouble(args[0]);
		double A2 = Double.parseDouble(args[1]);
		double B1 = Double.parseDouble(args[2]);
		double B2 = Double.parseDouble(args[3]);

		double[][] observation = {{A1, A2}, {B1, B2}};					// 样本观察表，行：(A,B)，列：(1,2)
		double[][] expectation = new double[2][2];						// 样本期望表

		double[] rowTotals = {A1 + A2, B1 + B2};						// 样本行总和数
		double[] colTotals = {A1 + B1, A2 + B2};						// 样本列总和数
		double grandTotal = A1 + A2 + B1 + B2;							// 样本总和数
		double OR = (A1 * B2) / (B1 * A2);								// 行方向变量的 OR 值

		double chisquare = 0;											// 计算卡方值
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				expectation[i][j] = rowTotals[i] * colTotals[j] / grandTotal;
				chisquare += (observation[i][j] - expectation[i][j]) * (observation[i][j] - expectation[i][j]) / expectation[i][j];
			}
		}

		// 以下是结果打印
		System.out.println("-------- 以下是结果打印 --------");
		System.out.printf("行合计：%s, %s\n", rowTotals[0], rowTotals[1]);
		System.out.printf("列合计：%s, %s\n", colTotals[0], colTotals[1]);
		System.out.println("总样本数：" + grandTotal);
		System.out.println("每格实际值与期望值");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.printf("%s (%s) \t", observation[i][j], expectation[i][j]);
			}
			System.out.println();
		}
		System.out.println("卡方值：" + chisquare);
		System.out.println("OR 值：" + OR);

		// 根据卡方值求 P 值
		try (BufferedReader br = new BufferedReader(new FileReader("chisquare-distribution-table.csv"))){
			String DELIMITER = ",";
			String line = "";
			ArrayList <String> lines = new ArrayList <String> ();
			while ((line = br.readLine()) != null) {
				lines.add(line.split(DELIMITER)[1]);
			};
			if (chisquare < 0.01){
				System.out.println("P 值小于 " + lines.get(0));
			} else if (chisquare <= lines.size() / 100){
				System.out.println("P 值: " + lines.get(Math.round((float)(chisquare * 100)) - 1));
			} else {
				System.out.println("P 值大于 " + lines.get(lines.size()-1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
