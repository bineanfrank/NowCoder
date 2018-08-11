package seclab.offer;

/**
 * description:
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
 * <p>
 * solution:
 * /*
 * 设N = abcde ,其中abcde分别为十进制中各位上的数字。
 * 如果要计算百位上1出现的次数，它要受到3方面的影响：百位上的数字，百位以下（低位）的数字，百位以上（高位）的数字。
 * ① 如果百位上数字为0，百位上可能出现1的次数由更高位决定。比如：12013，则可以知道百位出现1的情况可能是：100~199，1100~1199,2100~2199，，...，11100~11199，一共1200个。可以看出是由更高位数字（12）决定，并且等于更高位数字（12）乘以 当前位数（100）。
 * ② 如果百位上数字为1，百位上可能出现1的次数不仅受更高位影响还受低位影响。比如：12113，则可以知道百位受高位影响出现的情况是：100~199，1100~1199,2100~2199，，....，11100~11199，一共1200个。和上面情况一样，并且等于更高位数字（12）乘以 当前位数（100）。但同时它还受低位影响，百位出现1的情况是：12100~12113,一共114个，等于低位数字（113）+1。
 * ③ 如果百位上数字大于1（2~9），则百位上出现1的情况仅由更高位决定，比如12213，则百位出现1的情况是：100~199,1100~1199，2100~2199，...，11100~11199,12100~12199,一共有1300个，并且等于更高位数字+1（12+1）乘以当前位数（100）。
 * *
 */
public class B11184_整数中1出现的次数 {

    public static void main(String[] args) {
        B11184_整数中1出现的次数 b11184_整数中1出现的次数 = new B11184_整数中1出现的次数();
        int result = b11184_整数中1出现的次数.NumberOf1Between1AndN_Solution(13);
        System.out.println(result);
    }

    // 从低到高一次计算每一位上出现1的次数，然后相加即可
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        int curBit = 1;
        int current = 0;
        int before = 0;
        int after = 0;
        int tempN = n;
        while (tempN > 0) {
            current = (n / curBit) % 10; // 当前位的值
            before = n / (curBit * 10); // 高位的值
            after = n - (n / curBit) * curBit;// 低位的值
            if (current == 0) {
                count += before * curBit;
            } else if (current == 1) {
                count += (before * curBit + after + 1);
            } else {
                count += (before + 1) * curBit;
            }
            curBit *= 10;
            tempN /= 10;
        }
        return count;
    }
}
