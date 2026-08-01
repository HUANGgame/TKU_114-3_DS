public class AlgorithmComparisonReport {
    static class SortResult {
        String algorithm;
        int size;
        String dataType;
        long comparisons;

        SortResult(String algorithm, int size, String dataType, long comparisons) {
            this.algorithm = algorithm;
            this.size = size;
            this.dataType = dataType;
            this.comparisons = comparisons;
        }
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        String[] dataTypes = {"已排序", "反向", "固定亂序"};

        System.out.println("排序演算法比較報告");
        System.out.println("資料筆數,資料型態,選擇排序比較,插入排序比較,歸併排序比較");

        for (int size : sizes) {
            for (String dataType : dataTypes) {
                int[] original = buildData(size, dataType);

                SortResult selection = selectionSort(copy(original), size, dataType);
                SortResult insertion = insertionSort(copy(original), size, dataType);
                SortResult merge = mergeSort(copy(original), size, dataType);

                System.out.println(size + "," + dataType + ","
                        + selection.comparisons + ","
                        + insertion.comparisons + ","
                        + merge.comparisons);
            }
        }

        System.out.println();
        System.out.println("觀察結論：");
        System.out.println("1. 選擇排序比較次數主要由資料筆數決定，已排序、反向及固定亂序都相同。");
        System.out.println("2. 插入排序在已排序資料比較最少，反向資料比較最多，固定亂序介於兩者之間。");
        System.out.println("3. 歸併排序的比較次數成長較慢，資料筆數變大時比平方等級排序更適合大量資料。");
        System.out.println("4. 本報告使用16、128、1024筆資料與三種資料型態，不以單次小資料執行結果作為唯一判斷。");
    }

    static int[] buildData(int size, String dataType) {
        int[] data = new int[size];

        if (dataType.equals("已排序")) {
            for (int i = 0; i < size; i++) {
                data[i] = i + 1;
            }
        } else if (dataType.equals("反向")) {
            for (int i = 0; i < size; i++) {
                data[i] = size - i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                data[i] = (i * 37 + 11) % (size * 2);
            }
        }

        return data;
    }

    static int[] copy(int[] data) {
        int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }

    static SortResult selectionSort(int[] data, int size, String dataType) {
        long comparisons = 0;

        for (int start = 0; start < data.length - 1; start++) {
            int minIndex = start;
            for (int i = start + 1; i < data.length; i++) {
                comparisons++;
                if (data[i] < data[minIndex]) {
                    minIndex = i;
                }
            }

            if (minIndex != start) {
                int temp = data[start];
                data[start] = data[minIndex];
                data[minIndex] = temp;
            }
        }

        return new SortResult("選擇排序", size, dataType, comparisons);
    }

    static SortResult insertionSort(int[] data, int size, String dataType) {
        long comparisons = 0;

        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int position = i - 1;

            while (position >= 0) {
                comparisons++;
                if (data[position] <= key) {
                    break;
                }
                data[position + 1] = data[position];
                position--;
            }

            data[position + 1] = key;
        }

        return new SortResult("插入排序", size, dataType, comparisons);
    }

    static SortResult mergeSort(int[] data, int size, String dataType) {
        long[] comparisons = {0};
        int[] temp = new int[data.length];
        mergeSort(data, temp, 0, data.length - 1, comparisons);
        return new SortResult("歸併排序", size, dataType, comparisons[0]);
    }

    static void mergeSort(int[] data, int[] temp, int left, int right, long[] comparisons) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(data, temp, left, mid, comparisons);
        mergeSort(data, temp, mid + 1, right, comparisons);
        merge(data, temp, left, mid, right, comparisons);
    }

    static void merge(int[] data, int[] temp, int left, int mid, int right, long[] comparisons) {
        int leftIndex = left;
        int rightIndex = mid + 1;
        int output = left;

        while (leftIndex <= mid && rightIndex <= right) {
            comparisons[0]++;
            if (data[leftIndex] <= data[rightIndex]) {
                temp[output] = data[leftIndex];
                leftIndex++;
            } else {
                temp[output] = data[rightIndex];
                rightIndex++;
            }
            output++;
        }

        while (leftIndex <= mid) {
            temp[output] = data[leftIndex];
            leftIndex++;
            output++;
        }

        while (rightIndex <= right) {
            temp[output] = data[rightIndex];
            rightIndex++;
            output++;
        }

        for (int i = left; i <= right; i++) {
            data[i] = temp[i];
        }
    }
}
