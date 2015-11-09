package cn.baiing.util;

public class SimilarityMatch {

	/*
	 * 最长公共子序列（可以不连续）
	 */
	public static String levenshtein(String str1, String str2) {
		// 计算两个字符串的长度
		int len1 = str1.length();
		int len2 = str2.length();

		// 建立上面说的数组，比字符长度大一个空间
		int[][] dif = new int[len1 + 1][len2 + 1];
		// 赋初值，步骤B
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		// 计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 取3个值中最小的
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
						dif[i - 1][j] + 1);
			}
		}

		// System.out.println("字符串\"" + str1 + "\"与\"" + str2 + "\"的比较");
		// 取数组右下角的值，同样不同位置代表不同字符串的比较
		// System.out.println("差异步骤" + dif[len1][len2]);
		// 计算相似度
		float similarity = 1 - (float) dif[len1][len2]
				/ Math.max(str1.length(), str2.length());
		// System.out.println("相似度:" + similarity);
		return String.valueOf(similarity);
	}

	private static int min(int... is) {
		int min = Integer.MAX_VALUE;
		for (int i : is) {
			if (min > i) {
				min = i;
			}
		}
		return min;
	}

	/**
	 * 最长公共子串（连续）
	 * 在动态规划矩阵生成方式当中，每生成一行，前面的那一行就已经没有用了，因此这里只需使用一维数组，而不是常用的二位数组
	 * @param str1
	 * @param str2
	 */
	public static int getLCString(char[] str1, char[] str2) {
		int len1, len2;
		len1 = str1.length;
		len2 = str2.length;
		
		int maxLen = len1 > len2 ? len1 : len2;
		int[] max = new int[maxLen];// 保存最长子串长度的数组
		int[] maxIndex = new int[maxLen];// 保存最长子串长度最大索引的数组
		int[] c = new int[maxLen]; // 记录对角线上的相等值的个数

		int i, j;
		for (i = 0; i < len2; i++) {
			for (j = len1 - 1; j >= 0; j--) {
				if (str2[i] == str1[j]) {
					if ((i == 0) || (j == 0))
						c[j] = 1;
					else
						c[j] = c[j - 1] + 1;// 此时C[j-1]还是上次循环中的值，因为还没被重新赋值
				} else {
					c[j] = 0;
				}

				// 如果是大于那暂时只有一个是最长的,而且要把后面的清0;
				if (c[j] > max[0]) {
					max[0] = c[j]; // 记录对角线元素的最大值，之后在遍历时用作提取子串的长度
					maxIndex[0] = j; // 记录对角线元素最大值的位置

					for (int k = 1; k < maxLen; k++) {
						max[k] = 0;
						maxIndex[k] = 0;
					}
				}
				// 有多个是相同长度的子串
				else if (c[j] == max[0]) {
					for (int k = 1; k < maxLen; k++) {
						if (max[k] == 0) {
							max[k] = c[j];
							maxIndex[k] = j;
							break; // 在后面加一个就要退出循环了
						}
					}
				}
			}
//			for (int temp : c) {
//				System.out.print(temp);
//			}
//			System.out.println();
		}
		// 打印最长子字符串
		for (j = 0; j < maxLen; j++) {
			if (max[j] > 0) {
				return max[j];
			}
		}
		return 0;
	}
}
