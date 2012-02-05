import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
public class MatrixMultiplication {
	static double time;

	public static void main(String args[]) {
		//recursive brute force. may run out of time.
		
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		for (int i = 0; i < t;i += 1) {
			
			time = System.currentTimeMillis();
			//System.out.println(i);
			int n = in.nextInt();
			
			ArrayList<Matrix> matrices = new ArrayList<Matrix>();
			for (int j = 0;j < n; j += 1) {
				matrices.add(new Matrix(in.nextInt(), in.nextInt()));
			}
			
			Integer result = mul(matrices);
			if (result == null) {
				System.out.println(-1);
			} else {
				System.out.println(result);
			}
		}
	}

	private static Integer mul(ArrayList<Matrix> matrices) {
		if (System.currentTimeMillis() - time >= 400) {
			return null;
		}
		//System.out.println(matrices.size());
		if (matrices.size() == 1) {
			return matrices.get(0).m * matrices.get(0).n;
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < matrices.size(); i += 1) {
			for (int j = i + 1; j < matrices.size(); j += 1) {
				LinkedList<Matrix> matrix = matrices.get(i).mul(matrices.get(j));
				if (matrix.size() != 0) {
					for (Matrix m : matrix) {
						Matrix m1, m2;
	
						matrices.add(m);
						m1 = matrices.remove(j);
						m2 = matrices.remove(i);
					
						Integer val = mul(matrices);
						if (val != null) {
							max = Math.max(max, val);
						}
						
						matrices.add(i, m2);
						matrices.add(j, m1);
						matrices.remove(matrices.size() - 1);
					}
				}
			}
		}
		
		if (max == Integer.MIN_VALUE) {
			return null;
		} else {
			return max;
		}
	}
	
	static class Matrix {
		
		int m;
		int n;
		Matrix(int newM, int newN) {
			m = newM;
			n = newN;
		}
		
		LinkedList<Matrix> mul(Matrix matrix) {
			LinkedList<Matrix> poss = new LinkedList<Matrix>();
			if (n == matrix.m) {
				poss.add(new Matrix(m, matrix.n));
			}
			
			if (m == matrix.n) {
				poss.add(new Matrix(n, matrix.m));
			}
			
			return poss;
		}
	}
}
