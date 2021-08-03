/*
 * by LB
 * in Harbin Institute of Technology
 *
 * 2021 Spring Semester
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lab4 {

    public static void main(String[] args) throws IOException {
        List<String> dataPath = Arrays.asList("./data/order.txt", "./data/halfOrder.txt", "./data/reverse.txt");
        Random r = new Random();
        for(String itDataPath : dataPath)
        {
            System.out.println();
            System.out.println(itDataPath);
            List<Integer> inputs = getInputs(itDataPath);

            //number of times to run algorithm
            for(int i = 0; i < 10; i++)
            {
                int target = inputs.get(r.nextInt(inputs.size()));
                System.out.println(HammingDistanceMonotonicityTester(inputs, target));
            }
        }
    }

    /**
     * Get input list.
     *
     * @param dataPath file path of input data
     * @return a list containing input sequences
     * @throws IOException thrown if the dataPath is wrong.
     */
    private static List<Integer> getInputs(String dataPath) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(dataPath)
                )
        );
        String line;
        List<Integer> inputs = new ArrayList<>();
        while((line = br.readLine()) != null)
        {
            inputs.add(Integer.valueOf(line));
        }
        return inputs;
    }


    /**
     * Test the monotonicity of input list.
     *
     * @param inputs input data sequences
     * @param target random value in input list to binary search
     * @return "PASS" or "FAIL"
     */
    private static String HammingDistanceMonotonicityTester(List<Integer> inputs, int target) {

        System.out.print("target: " + target + "    ");

        int left = 0;
        int right = inputs.size() - 1;
        //Binary search.
        int cou = 0;
        System.out.print("loop cou: ");
        while (left <= right)
        {
            System.out.print(cou++ + "  ");
            int mid = (left + right) / 2;
            //If inputs is not increasing.
            if(inputs.get(left) > inputs.get(mid) || inputs.get(mid) > inputs.get(right))
            {
                return "FAIL";
            }

            if(inputs.get(mid) > target)
            {
                right = mid - 1;
            }
            else if(inputs.get(mid) < target)
            {
                left = mid + 1;
            }
            //Find target.
            else {
                return "PASS";
            }
        }
        //Fail to find target.
        return "FAIL";
    }
}
