import java.io.*;

/**
 * You can simply change a few lines to make this class read inputs from console,
 * and I think it's quick enough to be used in DSAA.
 */
public class Main {
	public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	public static PrintWriter output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	public static void LPS(byte[] p, int[] n) { //To generate partial match table.
		int piv = 2, cnt = 0;
		n[0] = -1;
		n[1] = 0;
		while (piv < p.length) {
			if (p[piv - 1] == p[cnt]) {
				n[piv] = ++cnt;
				++piv;
			} else if (cnt != 0) cnt = n[cnt];
			else ++piv;
		}
	}

	public static void KMP(byte[] s, byte[] p, int[] n) {   //Search string to find specific pattern.
		LPS(p, n);
		int i = 0, j = 0;
		while (i < s.length) {
			if (s[i] == p[j]) {
				++i;
				++j;
			}
			if (j == p.length) {
				output.println("Found pattern at index " + (i - j));
				j = n[j-1];
			} else if (i < s.length && p[j] != s[i])
				if (j != 0) j = n[j];
				else ++i;
		}
	}

	public static void main(String[] args) {
		byte[] s = "abaaaaabbbaba".getBytes();  //The string to be search.
		byte[] p = "abb".getBytes();    //The pattern should be found.
		int[] n = new int[p.length];
		KMP(s, p, n);
		output.flush();
	}
}
