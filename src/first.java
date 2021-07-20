public class first {

    //用来存储两个键相连经过其他键情况
    private static int[][] arr = new int[10][10];
    //动态初始化boolean数组 默认值为false
    private static boolean[] select = new boolean[10];

    public static int solution1 (int m, int n){
        //三行 1和3连线必须2已经连入
        arr[1][3] = arr[3][1] = 2;
        arr[4][6] = arr[6][4] = 5;
        arr[7][9] = arr[9][7] = 8;
        //三竖
        arr[1][7] = arr[7][1] = 4;
        arr[2][8] = arr[8][2] = 5;
        arr[9][3] = arr[3][9] = 6;
        //两条对角线
        arr[1][9] = arr[9][1] = 5;
        arr[3][7] = arr[7][3] = 5;
        //用于判断是否可以可以连入 二位数组 int默认值为0 没有跨节点下标为0就是true
        select[0] = true;

        if (m < 0 || n < m) {
            return 0;
        }
        // 1 3 7 9情况合并  2 4 6 8情况合并 5一种情况
        int total = 4 * calculateCount(m ,n, 1, 1) + 4 * calculateCount(m ,n, 2, 1)
                + calculateCount(m ,n, 5, 1);
        return total;
    }

    private static int calculateCount(int min, int max, int start, int len) {
        int count = 0;
        if (len > max) {
            return 0;
        }
        if (len >= min) {
            count++;
        }
        select[start] = true;
        for (int i = 1; i < 10; i++) {
            //当前节点start没有被选中 并且开始节点start到当前节点i不是跨节点连接
            if (!select[i] && select[arr[start][i]]) {
                count = count + calculateCount(min, max, i, len + 1);
            }
        }
        select[start] = false;
        return count;
    }

    public static void main(String[] args) {
        first a = new first();
        System.out.println(a.solution1(1,3));
    }
}

